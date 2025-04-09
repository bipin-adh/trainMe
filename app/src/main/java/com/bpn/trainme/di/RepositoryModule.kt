package com.bpn.trainme.di

import com.bpn.trainme.data.remote.ProductRemoteDataSource
import com.bpn.trainme.data.remote.api.ProductApi
import com.bpn.trainme.data.repository.ProductRepositoryImpl
import com.bpn.trainme.domain.repository.ProductRepository
import com.bpn.trainme.domain.usecase.GetProductDetailUseCase
import com.bpn.trainme.domain.usecase.GetProductListUseCase
import com.bpn.trainme.presentation.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideProductRemoteDataSource(productApi: ProductApi): ProductRemoteDataSource =
        ProductRemoteDataSource(api = productApi)

    @Provides
    @Singleton
    fun providesProductRepository(remoteDataSource: ProductRemoteDataSource): ProductRepository =
        ProductRepositoryImpl(
            remoteDataSource = remoteDataSource
        )


    @Provides
    @Singleton
    fun providesProductListUseCase(repository: ProductRepository) : GetProductListUseCase = GetProductListUseCase(repository = repository)


    @Provides
    @Singleton
    fun providesProductDetailUseCase(repository: ProductRepository) : GetProductDetailUseCase = GetProductDetailUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager{
        return NavigationManager()
    }
}