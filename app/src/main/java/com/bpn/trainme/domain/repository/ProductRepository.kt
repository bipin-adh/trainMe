package com.bpn.trainme.domain.repository

import com.bpn.trainme.domain.model.Product

interface ProductRepository {
    suspend fun getProductList(): List<Product>
    suspend fun getProductDetail(id: Int): Product

}
