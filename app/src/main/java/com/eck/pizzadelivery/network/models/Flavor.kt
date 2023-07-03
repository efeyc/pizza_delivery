package com.eck.pizzadelivery.network.models

import androidx.annotation.DrawableRes
import com.eck.pizzadelivery.R

class Flavor(val name: String, val price: Double) {

    // we are not receiving any image from api, added it manually to make UI a bit more appealing
    @DrawableRes
    fun getImage(): Int {
        return when {
            name.contains("Mozzarella") -> R.drawable.ic_pizza_mozzarella
            name.contains("Pepperoni") -> R.drawable.ic_pizza_pepperoni
            name.contains("Vegetarian") -> R.drawable.ic_pizza_vegi
            name.contains("cheese") -> R.drawable.ic_pizza_super_cheese
            else -> R.drawable.ic_pizza_default
        }
    }
}