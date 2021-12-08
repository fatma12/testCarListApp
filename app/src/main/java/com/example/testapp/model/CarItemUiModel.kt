package com.example.testapp.model

data class CarItemUiModel(
    val image: String?,
    val title: String,
    val price: String
)

fun CarDto.toUiModel() : CarItemUiModel {
    return CarItemUiModel(
        image = image,
        title = year.toString() + " " + make.manufacturer + " " + make.model,
        price = "$$price"
    )
}