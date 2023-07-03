package com.eck.pizzadelivery.network

import com.eck.pizzadelivery.network.models.Flavor

interface ApiService {

    suspend fun getFlavors(): NetworkState<List<Flavor>>
}