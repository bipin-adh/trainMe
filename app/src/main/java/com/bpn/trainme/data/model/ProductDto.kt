package com.bpn.trainme.data.model

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
)


