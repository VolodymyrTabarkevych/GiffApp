package ua.tabarkevych.composemvi.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ua.tabarkevych.composemvi.R

var Typography by mutableStateOf(Typography())

fun setTypography(font: AppFont) {
    Typography = Typography(defaultFontFamily = font.family)
}

sealed class AppFont(val name: String, val family: FontFamily) {

    object Default : AppFont(FONT_DEFAULT_NAME, FontFamily.Default)

    object Fredoka : AppFont(
        FONT_FREDOKA_NAME, FontFamily(
            Font(R.font.fredoka_regular),
            Font(R.font.fredoka_light, FontWeight.Light),
            Font(R.font.fredoka_medium, FontWeight.Medium),
            Font(R.font.fredoka_semi_bold, FontWeight.SemiBold),
            Font(R.font.fredoka_bold, FontWeight.Bold)
        )
    )

    object Oswald : AppFont(
        FONT_OSWALD_NAME, FontFamily(
            Font(R.font.oswald_regular),
            Font(R.font.oswald_light, FontWeight.Light),
            Font(R.font.oswald_medium, FontWeight.Medium),
            Font(R.font.oswald_semi_bold, FontWeight.SemiBold),
            Font(R.font.oswald_bold, FontWeight.Bold)
        )
    )

    object Poppins : AppFont(
        FONT_POPPINS_NAME, FontFamily(
            Font(R.font.poppins_regular),
            Font(R.font.poppins_light, FontWeight.Light),
            Font(R.font.poppins_medium, FontWeight.Medium),
            Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
            Font(R.font.poppins_bold, FontWeight.Bold)
        )
    )

    object Smooch : AppFont(
        FONT_SMOOCH_NAME, FontFamily(
            Font(R.font.smooch_sans_regular),
            Font(R.font.smooch_sans_light, FontWeight.Light),
            Font(R.font.smooch_sans_medium, FontWeight.Medium),
            Font(R.font.smooch_sans_semi_bold, FontWeight.SemiBold),
            Font(R.font.smooch_sans_bold, FontWeight.Bold)
        )
    )

    companion object {

        private const val FONT_DEFAULT_NAME = "Default"
        private const val FONT_FREDOKA_NAME = "Fredoka"
        private const val FONT_OSWALD_NAME = "Oswald"
        private const val FONT_POPPINS_NAME = "Poppins"
        private const val FONT_SMOOCH_NAME = "Smooch"

        fun getAll(): List<AppFont> {
            return listOf(Default, Fredoka, Oswald, Poppins, Smooch)
        }
    }
}