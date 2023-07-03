package com.eck.pizzadelivery.helper

import com.eck.pizzadelivery.network.models.Flavor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

object TestConstants {

    val exception = RuntimeException("test")

    @OptIn(ExperimentalCoroutinesApi::class)
    val dispatcher = UnconfinedTestDispatcher()

    val flavor1 = Flavor("name1", 10.0)
    val flavor2 = Flavor("name2", 11.0)
    val flavor3 = Flavor("name3", 12.0)

}