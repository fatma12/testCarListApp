package com.example.testapp.model

import com.example.testapp.utils.BASE_URL

data class CarItemUiModel(
    val image: String?,
    val title: String,
    val price: String
)

fun CarDto.toUiModel() : CarItemUiModel {
    return CarItemUiModel(
        image = BASE_URL + image,
        title = year.toString() + " " + make.manufacturer + " " + make.model,
        price = "$$price"
    )
}