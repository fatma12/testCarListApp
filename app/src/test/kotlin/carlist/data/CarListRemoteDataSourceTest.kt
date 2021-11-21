package carlist.data

import com.example.testapp.data.CarListRemoteDataSource
import com.example.testapp.data.CarListService
import com.example.testapp.model.CarDto
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertNotNull
import kotlin.test.fail

class CarListRemoteDataSourceTest {
    private val service: CarListService = mock()
    private val dataSource = CarListRemoteDataSource(service)

    @Test
    fun `create reminders with success`() = runBlocking {
        val carListDto = FakeModelFactory.carListDto
        // given that the service response with success
        withGetCarListSuccess(carDto = carListDto)

        // when requesting orders
        val result = dataSource.getCarList()

        // then there's one request to the service
        verify(service).getCarList()

        // then the correct inventory is returned
        result.fold(
            {
                fail("error not expected")
            },
            {
                assertNotNull(it)
            }
        )
    }

    @Test
    fun `create reminders with error`() = runBlocking {
        // given that the service responds with error
        withGetCarListError()

        // when requesting the inventory
        val result = dataSource.getCarList()

        // then error is returned
        Assert.assertTrue(result.isLeft)
    }

    private suspend fun  withGetCarListSuccess(carDto : List<CarDto>) {
        val result = Response.success(carDto)
        whenever(service.getCarList()).thenReturn(result)
    }

    private suspend fun  withGetCarListError() {
        val errorBody = "Error".toResponseBody("text/plain".toMediaTypeOrNull())
        val result = Response.error<List<CarDto>>(400, errorBody)
        whenever(
            service.getCarList()
        ).thenReturn(result)
    }
}