package com.eck.pizzadelivery.ui.screens.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.eck.pizzadelivery.network.ApiService
import com.eck.pizzadelivery.network.models.Flavor
import com.eck.pizzadelivery.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val apiService: ApiService,
    coroutineContext: CoroutineContext = Dispatchers.Default
) : BaseViewModel(coroutineContext) {

    var flavors by mutableStateOf(listOf<Flavor>())
        private set

    private val _orderState = MutableStateFlow<OrderState>(OrderState.None)
    var orderState = _orderState.asStateFlow()

    val selectedItems = mutableStateListOf<Flavor>()

    fun fetchFlavors() {
        selectedItems.clear()
        call({ apiService.getFlavors() }, successMethod = { response ->
            flavors = response
        })
    }

    fun showSuccess() {
        _orderState.value = OrderState.ShowSuccessPopup
    }

    override fun clearState() {
        super.clearState()
        _orderState.value = OrderState.None
    }

    sealed interface OrderState {
        object None : OrderState
        object ShowSuccessPopup : OrderState
    }
}