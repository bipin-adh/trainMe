package com.bpn.trainme.repository

import com.bpn.trainme.model.ProductDetailsDto
import com.bpn.trainme.util.ApiState

interface Repository {
    suspend fun getProductDetails(): ApiState<ProductDetailsDto>
}