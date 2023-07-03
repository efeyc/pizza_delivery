package com.eck.pizzadelivery.ui.screens.confirmation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eck.pizzadelivery.R
import com.eck.pizzadelivery.ui.components.PrimaryButton
import com.eck.pizzadelivery.ui.screens.menu.MenuViewModel
import com.eck.pizzadelivery.ui.screens.menu.MenuViewModel.OrderState.ShowSuccessPopup
import com.eck.pizzadelivery.ui.theme.Typography

@Composable
fun ConfirmationScreen(
    viewModel: MenuViewModel,
    onOrderCompleted: () -> Unit
) {
    val orderState = viewModel.orderState.collectAsState()
    val items = viewModel.selectedItems

    if (items.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {

            Row {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = items[0].getImage()),
                    contentDescription = "menu image",
                    alignment = Alignment.Center
                )
                if (items.size > 1) {
                    Image(
                        modifier = Modifier.padding(start = 16.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(id = items[1].getImage()),
                        contentDescription = "menu image",
                        alignment = Alignment.Center
                    )
                }
            }
            val flavorText = if (items.size == 1) {
                stringResource(id = R.string.flavor_message_single, items[0].name)
            } else {
                stringResource(id = R.string.flavor_message_double, items[0].name, items[1].name)
            }
            val totalPrice = if (items.size == 1) items[0].price else (items[0].price + items[1].price) / 2
            val priceText = stringResource(id = R.string.total_price, totalPrice.toString())
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = flavorText,
                style = Typography.bodyMedium
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                text = priceText,
                style = Typography.bodySmall
            )

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.confirm),
                onClick = {
                    viewModel.showSuccess()
                }
            )
        }

        if (orderState.value == ShowSuccessPopup) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.clearState()
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.clearState()
                        onOrderCompleted()
                    })
                    { Text(text = stringResource(id = R.string.ok)) }
                },
                text = { Text(text = stringResource(id = R.string.successful_message)) }
            )
        }
    }
}