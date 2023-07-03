package com.eck.pizzadelivery.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.eck.pizzadelivery.R
import com.eck.pizzadelivery.ui.base.BaseViewModel
import com.eck.pizzadelivery.ui.theme.Blue

@Composable
fun BaseScreen(
    baseViewModel: BaseViewModel? = null,
    enableLoading: Boolean = true,
    content: @Composable () -> Unit
) {
    content()

    baseViewModel?.let { viewModel ->
        val state = viewModel.baseState.collectAsState()
        when (state.value) {
            is BaseViewModel.BaseState.Error -> {
                (state.value as? BaseViewModel.BaseState.Error)?.error?.let { response ->
                    AlertDialog(
                        onDismissRequest = {
                            viewModel.clearState()
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.clearState()
                            })
                            { Text(text = stringResource(id = R.string.ok)) }
                        },
                        title = { Text(text = stringResource(id = R.string.error)) },
                        text = { Text(text = response.take(500)) }
                    )
                }
            }

            BaseViewModel.BaseState.Loading -> {
                if (enableLoading) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Blue
                        )
                    }
                } else { // NOOP
                }
            }

            else -> { // NOOP
            }
        }
    }

}