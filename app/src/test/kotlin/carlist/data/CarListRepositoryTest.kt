package carlist.data

import com.example.testapp.data.CarListRemoteDataSource
import com.example.testapp.data.CarListRepository
import com.example.testapp.model.CarDto
import com.example.testapp.network.Either
import com.example.testapp.network.Failure
import com.example.testapp.network.ServerErrorType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class CarListRepositoryTest {
    private val dataSource: CarListRemoteDataSource = mock()
    private val repository = CarListRepository(dataSource)

    //region get car list
    @Test
    fun `get car list with success`() = runBlocking {
        val carListDto = FakeModelFactory.carListDto
        // given that the dataSource responds with success
        withGetCarListSuccess(carListDto)

        // when requesting the result
        val result = repository.getCarList()

        // then there's one request to the dataSource
        verify(dataSource).getCarList()
        // then the correct resend status is returned
        result.fold(
            {
                fail("not error expected")
            },
            {
                assertTrue(result.isRight)
            }
        )
    }

    @Test
    fun `create reminder with error`() = runBlocking {
        // given that the dataSource responds with error
        withGetCarListFailed()

        // when requesting the result
        val result = repository.getCarList()

        // then error is returned
     //   Assert.assertTrue(result.isLeft)
    }

    private suspend fun withGetCarListSuccess(result: List<CarDto>) {
        whenever(dataSource.getCarList()).thenReturn(Either.Right(result))
    }

    private suspend fun withGetCarListFailed() {
        whenever(dataSource.getCarList()).thenReturn(
            Either.Left(
                Failure.ServerError(
                    ServerErrorType.UNKNOWN
                )
            )
        )
    }
}

