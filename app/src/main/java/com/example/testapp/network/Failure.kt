package com.example.testapp.network

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object TimeoutException : Failure()
    data class ServerError(val type: ServerErrorType, val message: String? = null) : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}

enum class ServerErrorType {
    GENERIC_ERROR,
    SERVER_UNAVAILABLE,
    UNKNOWN
}