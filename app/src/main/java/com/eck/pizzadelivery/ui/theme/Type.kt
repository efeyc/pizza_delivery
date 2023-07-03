package com.eck.pizzadelivery.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.eck.pizzadelivery.R

val alexandria = FontFamily(
    Font(R.font.alexandria_regular), // 400
    Font(R.font.alexandria_medium, weight = FontWeight.Medium), // 500
    Font(R.font.alexandria_semi_bold, weight = FontWeight.SemiBold), // 600
)

val darkTextStyle = TextStyle(
    fontFamily = alexandria,
    color = DarkText
)

val Typography = Typography(
    bodyMedium = darkTextStyle.copy(
        fontFamily = alexandria,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodySmall = darkTextStyle.copy(
        fontFamily = alexandria,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = darkTextStyle.copy(
        fontFamily = alexandria,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = alexandria,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = alexandria,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    titleLarge = TextStyle(
        fontFamily = alexandria,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    titleMedium = TextStyle(
        fontFamily = alexandria,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)