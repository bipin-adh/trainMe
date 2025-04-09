package com.bpn.trainme.data.model

data class ProductResponseDto(
    val products: List<ProductDto>,
    val total : Int,
    val skip : Int,
    val limit : Int
)