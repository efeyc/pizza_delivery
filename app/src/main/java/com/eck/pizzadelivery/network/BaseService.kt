package com.eck.pizzadelivery.network

import retrofit2.Response

open class BaseService {

    inline fun <reified T> call(m: () -> Response<T>): NetworkState<T> {
        val response = m.invoke()

        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(createErrorMessage(response), response.code())
            }
        } else {
            NetworkState.Error(createErrorMessage(response), response.code())
        }
    }


    fun <T> createErrorMessage(response: Response<T>): String {
        return response.errorBody()?.string() ?: "Something went wrong"
    }
}