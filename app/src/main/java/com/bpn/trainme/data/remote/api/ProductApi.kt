package com.bpn.trainme.data.remote.api

import com.bpn.trainme.data.model.ProductDto
import com.bpn.trainme.data.model.ProductResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products")
    suspend fun getProductList(): ProductResponseDto

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id") productId: Int): ProductDto

}