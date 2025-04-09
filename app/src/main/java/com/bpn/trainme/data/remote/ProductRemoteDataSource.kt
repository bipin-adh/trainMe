package com.bpn.trainme.data.remote

import com.bpn.trainme.data.model.ProductDto
import com.bpn.trainme.data.model.ProductResponseDto
import com.bpn.trainme.data.remote.api.ProductApi
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val api: ProductApi) {
    suspend fun getProductList(): ProductResponseDto = api.getProductList()
    suspend fun getProductDetail(id: Int): ProductDto = api.getProductDetails(id)
}
