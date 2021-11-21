package com.example.testapp.data

import com.example.testapp.model.CarDto
import com.example.testapp.network.Either
import com.example.testapp.network.Failure
import com.example.testapp.utils.safeRequest

class CarListRemoteDataSource (private val api: CarListService) : CarListDataSource {

    override suspend fun getCarList(): Either<Failure, List<CarDto>> {
        return safeRequest(
            call = {
                api.getCarList()
            },
            default = emptyList(),
            errorMessage = "Error getting car list"
        )
    }
}