package com.example.testapp.data

import com.example.testapp.model.CarDto
import retrofit2.Response
import retrofit2.http.GET

interface CarListService {

    @GET("https://swapi.dev/api/people/1/")
    suspend fun getCarList(): Response<List<CarDto>>

}
