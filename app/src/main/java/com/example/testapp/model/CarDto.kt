package com.example.testapp.model

import com.google.gson.annotations.SerializedName

data class CarDto(
    @SerializedName("make")
    val make : MakeDto,
    @SerializedName("year")
    val year : Int,
    @SerializedName("image")
    val image : String?,
    @SerializedName("price")
    val price : Int
)

data class MakeDto(
    @SerializedName("manufacturer")
    val manufacturer : String,
    @SerializedName("model")
    val model : String
)