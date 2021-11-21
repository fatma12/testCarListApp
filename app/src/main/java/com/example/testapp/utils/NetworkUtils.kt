package com.example.testapp.utils

import com.example.testapp.network.Either
import com.example.testapp.network.Failure
import com.example.testapp.network.ServerErrorType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException

/**
 * Wrap a suspending API [call] in try/catch. In case an exception is thrown, a [Failure] is
 * created
 */
suspend fun <R : Any> safeRequest(
    call: suspend () -> Response<R>,
    default: R, // when request is success but no body is found
    errorMessage: String,
    mapError: (response: Response<R>) -> Failure = {
        defaultErrorMap(it)
    }
): Either<Failure, R> {
    return try {
        val response = call.invoke()
        when (response.isSuccessful) {
            true -> Either.Right(response.body() ?: default)
            false -> Either.Left(mapError(response))
        }
    } catch (e: Exception) {
        when (e) {
            is SocketTimeoutException, is InterruptedIOException -> {
                Either.Left(Failure.TimeoutException)
            }
            is IOException -> {
                Either.Left(Failure.NetworkConnection)
            }
            else -> {
                Either.Left(
                    Failure.ServerError(
                        ServerErrorType.GENERIC_ERROR,
                        e.localizedMessage ?: errorMessage
                    )
                )
            }
        }
    } catch (e: Throwable) {
        Either.Left(
            Failure.ServerError(
                ServerErrorType.GENERIC_ERROR,
                e.localizedMessage ?: errorMessage
            )
        )
    }
}

fun <T> defaultErrorMap(response: Response<T>): Failure {
    return when (response.code()) {
        408 -> Failure.TimeoutException
        in 400..499 -> Failure.ServerError(
            ServerErrorType.GENERIC_ERROR,
            extractErrorMessage(response)
        )
        503 -> Failure.ServerError(ServerErrorType.SERVER_UNAVAILABLE)
        else -> Failure.ServerError(ServerErrorType.UNKNOWN)
    }
}

@Throws(JSONException::class)
private fun <T> extractErrorMessage(response: Response<T>): String? {
    var message: String? = "Something went wrong.\nPlease try again."

    val body = String(response.errorBody()!!.bytes())
    val json = JSONObject(body)
    if (json.length() > 0) {
        val key = json.keys().next()
        val value = json[key]
        if (value is String) {
            message = value
        } else if (value is JSONArray) {
            message = value.getString(0)
        }
    }
    return message
}