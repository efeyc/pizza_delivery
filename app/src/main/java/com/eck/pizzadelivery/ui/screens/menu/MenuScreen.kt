package com.eck.pizzadelivery.ui.screens.menu

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.eck.pizzadelivery.R
import com.eck.pizzadelivery.ui.components.BaseScreen
import com.eck.pizzadelivery.ui.components.PrimaryButton

@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
    onContinueClicked: () -> Unit
) {
    val context = LocalContext.current

    BaseScreen(baseViewModel = viewModel) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .weight(1f),
                columns = GridCells.Fixed(2)
            ) {
                items(viewModel.flavors) {
                    MenuItem(it, viewModel.selectedItems.contains(it)) {
                        if (viewModel.selectedItems.contains(it)) {
                            viewModel.selectedItems.remove(it)
                        } else if (viewModel.selectedItems.size < 2) {
                            viewModel.selectedItems.add(it)
                        } else {
                            Toast.makeText(context, R.string.max_flavor_warning, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                enable = viewModel.selectedItems.isNotEmpty(),
                text = stringResource(id = R.string.next),
                onClick = onContinueClicked
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchFlavors()
    }
}