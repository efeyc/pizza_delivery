package com.eck.pizzadelivery.network

import com.eck.pizzadelivery.network.models.Flavor
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("tests/pizzas.json")
    suspend fun getFlavors(): Response<List<Flavor>>

}
