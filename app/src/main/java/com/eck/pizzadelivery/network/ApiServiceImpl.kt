package com.eck.pizzadelivery.network

import com.eck.pizzadelivery.network.models.Flavor
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val api: Api) : BaseService(), ApiService {

    override suspend fun getFlavors() : NetworkState<List<Flavor>> = call { api.getFlavors() }
}