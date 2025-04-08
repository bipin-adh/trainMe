package com.bpn.trainme.repository

import com.bpn.trainme.model.ProductDetailsDto
import com.bpn.trainme.network.ApiService
import com.bpn.trainme.util.ApiState
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : Repository {

    override suspend fun getProductDetails(): ApiState<ProductDetailsDto> = try {
        ApiState.Success(apiService.getProductDetails())
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }
}