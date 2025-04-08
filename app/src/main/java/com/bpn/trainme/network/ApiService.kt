package com.bpn.trainme.network

import com.bpn.trainme.model.ProductDetailsDto
import retrofit2.http.GET

interface ApiService {

    @GET("products/1") // TODO: Set API Endpoint
    suspend fun getProductDetails(): ProductDetailsDto // TODO: Set API Response
}