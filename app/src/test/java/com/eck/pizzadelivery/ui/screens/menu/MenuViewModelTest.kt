package com.eck.pizzadelivery.ui.screens.menu

import com.eck.pizzadelivery.helper.InstantExecutorExtension
import com.eck.pizzadelivery.helper.TestConstants.dispatcher
import com.eck.pizzadelivery.helper.TestConstants.exception
import com.eck.pizzadelivery.helper.TestConstants.flavor1
import com.eck.pizzadelivery.helper.TestConstants.flavor2
import com.eck.pizzadelivery.helper.TestConstants.flavor3
import com.eck.pizzadelivery.network.ApiService
import com.eck.pizzadelivery.network.NetworkState
import com.eck.pizzadelivery.ui.screens.menu.MenuViewModel.OrderState.None
import com.eck.pizzadelivery.ui.screens.menu.MenuViewModel.OrderState.ShowSuccessPopup
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
@ExperimentalCoroutinesApi
internal class MenuViewModelTest {

    @MockK
    lateinit var apiService: ApiService

    private lateinit var viewModel: MenuViewModel

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        viewModel = MenuViewModel(apiService, dispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should show success popup`() {

        viewModel.showSuccess()
        assertEquals(ShowSuccessPopup, viewModel.orderState.value)
    }

    @Test
    fun `should clear order state`() {

        viewModel.clearState()
        assertEquals(None, viewModel.orderState.value)
    }

    @Test
    fun `should match flavor list when api call is successful`() {

        val expectedList = listOf(flavor1, flavor2, flavor3)
        coEvery { apiService.getFlavors() }.returns(NetworkState.Success(expectedList))

        viewModel.fetchFlavors()

        assertTrue(viewModel.selectedItems.isEmpty())
        assertEquals(expectedList, viewModel.flavors)
    }

    @Test
    fun `should match empty flavor list when api call fails`() {

        coEvery { apiService.getFlavors() }.returns(NetworkState.Error("error message", 500))

        viewModel.fetchFlavors()

        assertTrue(viewModel.selectedItems.isEmpty())
        assertTrue(viewModel.flavors.isEmpty())
    }

    @Test
    fun `should match empty flavor list when api call throws exception`() {

        coEvery { apiService.getFlavors() }.throws(exception)

        viewModel.fetchFlavors()

        assertTrue(viewModel.selectedItems.isEmpty())
        assertTrue(viewModel.flavors.isEmpty())
    }
}