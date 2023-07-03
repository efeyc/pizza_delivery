package com.eck.pizzadelivery.objects

sealed class Screen(val route: String) {
    object Menu : Screen("menu")
    object Confirmation : Screen("confirmation")
}