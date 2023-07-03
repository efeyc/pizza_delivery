package com.eck.pizzadelivery.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eck.pizzadelivery.ui.theme.Blue
import com.eck.pizzadelivery.ui.theme.PizzaDeliveryTheme
import com.eck.pizzadelivery.ui.theme.PressedPrimaryButtonBackground
import com.eck.pizzadelivery.ui.theme.PressedPrimaryButtonContentColor
import com.eck.pizzadelivery.ui.theme.Typography
import com.eck.pizzadelivery.ui.theme.White

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enable: Boolean = true,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isPressed) PressedPrimaryButtonBackground else Blue,
            contentColor = if (isPressed) PressedPrimaryButtonContentColor else White,
            disabledContainerColor = PressedPrimaryButtonBackground,
            disabledContentColor = PressedPrimaryButtonContentColor
        ),
        enabled = enable,
        shape = RoundedCornerShape(8.dp),
        interactionSource = interactionSource,
        contentPadding = ButtonDefaults.ContentPadding,
    ) {
        Text(text = text)
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Blue,
        ),
        border = BorderStroke(1.dp, Blue),
        shape = RoundedCornerShape(8.dp),
        contentPadding = ButtonDefaults.ContentPadding,
    ) {
        Text(text = text, style = Typography.bodySmall.copy(color = Blue))
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    PizzaDeliveryTheme {
        Column {
            PrimaryButton(text = "Ok") {

            }
            SecondaryButton(text = "Cancel") {

            }
        }
    }
}