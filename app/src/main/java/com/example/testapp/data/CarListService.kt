package com.example.testapp.data

import com.example.testapp.model.CarDto
import retrofit2.Response
import retrofit2.http.GET

interface CarListService {

    @GET("cars.json")
    suspend fun getCarList(): Response<List<CarDto>>

}
