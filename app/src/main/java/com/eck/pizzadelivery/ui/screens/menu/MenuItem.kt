package com.eck.pizzadelivery.ui.screens.menu


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eck.pizzadelivery.network.models.Flavor
import com.eck.pizzadelivery.ui.theme.Blue
import com.eck.pizzadelivery.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItem(
    flavor: Flavor,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    val border = if (isSelected) BorderStroke(2.dp, Blue) else null
    Card(
        modifier = Modifier
            .padding(8.dp, 8.dp, 8.dp, 8.dp),
        shape = RoundedCornerShape(8.dp),
        border = border,
        onClick = onItemClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = flavor.getImage()),
                contentDescription = "menu image",
                alignment = Alignment.Center
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = flavor.name,
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
                text = "$ ${flavor.price}",
                style = Typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

