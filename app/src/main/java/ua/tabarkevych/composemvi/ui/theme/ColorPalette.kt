package ua.tabarkevych.composemvi.ui.theme

import androidx.compose.material.Colors

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

var ColorPalette by mutableStateOf(AppTheme.Light.colorPalette)

fun setColorPalette(appTheme: AppTheme) {
    ColorPalette = appTheme.colorPalette
}

sealed class AppTheme(val name: String, val colorPalette: Colors) {

    object Light : AppTheme(
        name = LIGHT_THEME_NAME, colorPalette = lightColors(
            primary = Alabaster,
            primaryVariant = Alabaster,
            onPrimary = Color.Black,
            background = AthensGray,
            surface = AthensGray
        )
    )

    object Dark : AppTheme(
        name = DARK_THEME_NAME, colorPalette = darkColors(
            primary = OuterSpace,
            onPrimary = Color.White,
            background = Color.Black,
            surface = Color.Black
        )
    )

    object Green : AppTheme(
        name = GREEN_THEME_NAME, colorPalette = lightColors(
            primary = Jade,
            onPrimary = Color.Black,
            background = AthensGray
        )
    )

    companion object {

        private const val LIGHT_THEME_NAME = "Light"
        private const val DARK_THEME_NAME = "Dark"
        private const val GREEN_THEME_NAME = "Green"

        fun getAll(): List<AppTheme> {
            return listOf(Light, Dark, Green)
        }
    }
}