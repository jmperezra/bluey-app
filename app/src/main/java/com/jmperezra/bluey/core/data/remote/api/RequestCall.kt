package com.jmperezra.bluey.core.data.remote.api

import com.jmperezra.bluey.core.domain.ErrorApp
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (exception: Throwable) {
        return when (exception) {
            is ConnectException -> Result.failure(ErrorApp.InternetError)
            is UnknownHostException -> Result.failure(ErrorApp.ServerError)
            is SocketTimeoutException -> Result.failure(ErrorApp.InternetError)
            else -> Result.failure(ErrorApp.UnknownError)
        }
    }
    return if (response.isSuccessful && response.body() != null) {
        Result.success(response.body()!!)
    } else {
        when (response.code()) {
            //401, 403 -> Result.failure(ErrorApp.InvalidCredentialsError)
            else -> Result.failure(ErrorApp.UnknownError)
        }
    }
}