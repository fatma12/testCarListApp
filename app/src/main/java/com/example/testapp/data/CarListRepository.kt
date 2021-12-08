package com.example.testapp.data

import com.example.testapp.model.CarDto
import com.example.testapp.model.MakeDto
import com.example.testapp.network.Either
import com.example.testapp.network.Failure

class CarListRepository(private val carListRemoteDataSource: CarListRemoteDataSource) {

    suspend fun getCarList(): Either<Failure, List<CarDto>> {
        return carListRemoteDataSource.getCarList().right(
            listOf(
                CarDto(
                    MakeDto("Honda", "Civic"),
                    2019,
                    "https://cdn.shopify.com/s/files/1/2650/0372/products/ford-fusion-2017_dad4dd0b-026c-43b4-8cb9-293a117f6545_1024x1024@2x.png?v=1574110130",
                    17500
                ),
                CarDto(
                    MakeDto("BMW", "X7"),
                    2020,
                    "https://www.ft.com/__origami/service/image/v2/images/raw/http%3A%2F%2Fcom.ft.imagepublish.upp-prod-eu.s3.amazonaws.com%2Fb55be526-b332-11e7-8007-554f9eaa90ba?fit=scale-down&source=next&width=700",
                    17500
                ),
                CarDto(
                    MakeDto("Hyundai", "Atos Prime"),
                    2019,
                    "https://cdn.shopify.com/s/files/1/2650/0372/products/ford-fusion-2017_dad4dd0b-026c-43b4-8cb9-293a117f6545_1024x1024@2x.png?v=1574110130",
                    17500
                ),
                CarDto(
                    MakeDto("Honda", "Civic"),
                    2019,
                    "https://www.ft.com/__origami/service/image/v2/images/raw/http%3A%2F%2Fcom.ft.imagepublish.upp-prod-eu.s3.amazonaws.com%2Fb55be526-b332-11e7-8007-554f9eaa90ba?fit=scale-down&source=next&width=700",
                    17500
                ),
                CarDto(
                    MakeDto("BMW", "X7"),
                    2020,
                    "https://cdn.shopify.com/s/files/1/2650/0372/products/ford-fusion-2017_dad4dd0b-026c-43b4-8cb9-293a117f6545_1024x1024@2x.png?v=1574110130",
                    17500
                ),
                CarDto(
                    MakeDto("Hyundai", "Atos Prime"),
                    2019,
                    "https://www.ft.com/__origami/service/image/v2/images/raw/http%3A%2F%2Fcom.ft.imagepublish.upp-prod-eu.s3.amazonaws.com%2Fb55be526-b332-11e7-8007-554f9eaa90ba?fit=scale-down&source=next&width=700",
                    17500
                ),
                CarDto(
                    MakeDto("Honda", "Civic"),
                    2019,
                    "https://cdn.shopify.com/s/files/1/2650/0372/products/ford-fusion-2017_dad4dd0b-026c-43b4-8cb9-293a117f6545_1024x1024@2x.png?v=1574110130",
                    17500
                ),
                CarDto(
                    MakeDto("BMW", "X7"),
                    2020,
                    "https://www.ft.com/__origami/service/image/v2/images/raw/http%3A%2F%2Fcom.ft.imagepublish.upp-prod-eu.s3.amazonaws.com%2Fb55be526-b332-11e7-8007-554f9eaa90ba?fit=scale-down&source=next&width=700",
                    17500
                ),
                CarDto(
                    MakeDto("Hyundai", "Atos Prime"),
                    2019,
                    "https://cdn.shopify.com/s/files/1/2650/0372/products/ford-fusion-2017_dad4dd0b-026c-43b4-8cb9-293a117f6545_1024x1024@2x.png?v=1574110130",
                    17500
                ),
            )
        )
    }
}