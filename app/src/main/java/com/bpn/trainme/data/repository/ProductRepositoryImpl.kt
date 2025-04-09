package com.bpn.trainme.data.repository

import com.bpn.trainme.data.model.ProductDto
import com.bpn.trainme.data.model.ProductResponseDto
import com.bpn.trainme.data.remote.ProductRemoteDataSource
import com.bpn.trainme.domain.model.Product
import com.bpn.trainme.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource
): ProductRepository {
    override suspend fun getProductList(): List<Product> {
        val response : ProductResponseDto = remoteDataSource.getProductList()

        return response.products.map { productDto->
            Product(
                id = productDto.id,
                title = productDto.title,
                description = productDto.description,
                price = productDto.price,
                thumbnail = productDto.thumbnail
            )
        }
    }

    override suspend fun getProductDetail(id: Int): Product {
        val productDto : ProductDto = remoteDataSource.getProductDetail(id)
        return Product(
            id = productDto.id,
            title = productDto.title,
            description = productDto.description,
            price = productDto.price,
            thumbnail = productDto.thumbnail
        )
    }
}