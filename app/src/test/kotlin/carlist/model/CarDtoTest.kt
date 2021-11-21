package carlist.model

import com.example.testapp.model.CarDto
import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals

class CarDtoTest {

    @Test
    fun `deserialize valid response`() {
        // Given
        val json = """
            {
               "make": {
                 "manufacturer": "Honda",
                 "model": "Civic"
               },
               "color": "Red", 
               "year": "2019", 
               "configuration": {
                  "body": "Saloon", 
                  "cylinders": 4, 
                  "horsepower": 130
               },
               "origin": "Japan",
               "mpg": 19,
               "image": "images/honda_civic_1.jpg", 
               "price": 17500
            }
        """
        // when
        val carDto = Gson().fromJson(json, CarDto::class.java)

        // Then
        assertEquals("Honda", carDto.make.manufacturer)
        assertEquals("Civic", carDto.make.model)
        assertEquals(2019, carDto.year)
        assertEquals(17500, carDto.price)
    }
}