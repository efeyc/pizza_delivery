package com.eck.pizzadelivery.network

sealed class NetworkState<out T> {

    data class Success<out T>(val data: T) : NetworkState<T>()

    data class Error<T>(val error: String, val code: Int) : NetworkState<T>()

}