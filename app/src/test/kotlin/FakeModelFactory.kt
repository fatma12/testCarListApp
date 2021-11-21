import com.example.testapp.model.CarDto
import com.example.testapp.model.MakeDto

class FakeModelFactory {

    companion object {

        val carListDto = listOf(
            CarDto(
                make = MakeDto(manufacturer = "Honda", model = "Civic"),
                year = 2021,
                image = "https://afterpay-mobile-interview.s3.amazonaws.com/images/honda_civic_1.jpg",
                price = 17500
            )
        )
    }
}