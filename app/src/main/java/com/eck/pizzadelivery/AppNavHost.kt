package com.eck.pizzadelivery

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.eck.pizzadelivery.objects.Screen
import com.eck.pizzadelivery.ui.screens.confirmation.ConfirmationScreen
import com.eck.pizzadelivery.ui.screens.menu.MenuScreen
import com.eck.pizzadelivery.ui.screens.menu.MenuViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    val menuViewModel: MenuViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Menu.route
    ) {
        composable(Screen.Menu.route) {
            MenuScreen(menuViewModel) {
                navController.navigate(Screen.Confirmation.route)
            }
        }
        composable(Screen.Confirmation.route) {
            ConfirmationScreen(menuViewModel) {
                navController.navigate(Screen.Menu.route) {
                    popUpTo(0)
                }
            }
        }
    }
}