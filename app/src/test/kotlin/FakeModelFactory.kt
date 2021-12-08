import com.example.testapp.model.CarDto
import com.example.testapp.model.MakeDto

class FakeModelFactory {

    companion object {

        val carListDto = listOf(
            CarDto(
                make = MakeDto(manufacturer = "Honda", model = "Civic"),
                year = 2021,
                image = "https://www.ft.com/__origami/service/image/v2/images/raw/http%3A%2F%2Fcom.ft.imagepublish.upp-prod-eu.s3.amazonaws.com%2Fb55be526-b332-11e7-8007-554f9eaa90ba?fit=scale-down&source=next&width=700",
                price = 17500
            )
        )
    }
}