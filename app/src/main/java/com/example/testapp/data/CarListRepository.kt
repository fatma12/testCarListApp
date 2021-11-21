package com.example.testapp.data

import com.example.testapp.model.CarDto
import com.example.testapp.network.Either
import com.example.testapp.network.Failure

class CarListRepository (private val carListRemoteDataSource: CarListRemoteDataSource) {

    suspend fun getCarList() : Either<Failure, List<CarDto>> {
        return carListRemoteDataSource.getCarList()
    }
}