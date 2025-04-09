package com.bpn.trainme.domain.usecase

import com.bpn.trainme.domain.model.Product
import com.bpn.trainme.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: ProductRepository) {
    suspend operator fun invoke(): List<Product>{
        return repository.getProductList()
    }
}