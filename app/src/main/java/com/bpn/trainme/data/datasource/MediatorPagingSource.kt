package com.bpn.trainme.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bpn.trainme.data.mapper.ExerciseMapper
import com.bpn.trainme.domain.model.Exercise
import java.net.UnknownHostException

class MediatorPagingSource(
    private val localDataSource : LocalExerciseDataSource,
    private val remoteDataSource : RemoteExerciseDataSource
) : PagingSource<Int, Exercise>(){

    companion object {
        private const val PAGE_SIZE = 10
    }

    override fun getRefreshKey(state: PagingState<Int, Exercise>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Exercise> {
        val page = params.key ?: 0
        val offset = page * PAGE_SIZE

        return try {
            //try local data first
            val localExercise = localDataSource.getExercises(page, 0, PAGE_SIZE)
            if (localExercise.isNotEmpty()){
                return LoadResult.Page(
                    data = localExercise,
                    prevKey = if (page > 0) page - 1 else null,
                    nextKey = if (localExercise.size == PAGE_SIZE) page + 1 else null
                )
            }

            //fetch from remote if no local data
            val response = remoteDataSource.getExercises(offset, PAGE_SIZE)

            val totalPages = response.data?.totalPages?:0

            if(response.success){
                val entities = response.data?.exercises?.mapNotNull {
                    ExerciseMapper.toEntity(it, page)
                }

                entities?.let {
                    localDataSource.insertExercises(it)
                }

                val exercises = entities?.map { ExerciseMapper.toDomain(it) }

                exercises?.let {
                    LoadResult.Page(
                        data = exercises,
                        prevKey = if (page > 0) page - 1 else null,
                        nextKey = if (exercises.size == PAGE_SIZE && page < totalPages - 1) page + 1 else null
                    )
                }?: LoadResult.Error(Exception("Error fetching data"))
            }else{
                LoadResult.Error(Exception("Error fetching data"))
            }

        }catch (e: UnknownHostException){
            LoadResult.Error(e)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}