package ua.tabarkevych.composemvi.ui.dimens

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val LocalTextSize = compositionLocalOf { TextSize() }

val MaterialTheme.textSize: TextSize
    @Composable
    @ReadOnlyComposable
    get() = LocalTextSize.current

data class TextSize(
    val small: TextUnit = 10.sp,
    val x_small: TextUnit = 12.sp,
    val normal: TextUnit = 14.sp,
    val medium: TextUnit = 16.sp,
    val x_medium: TextUnit = 18.sp,
    val xx_medium: TextUnit = 20.sp,
    val xxx_medium: TextUnit = 22.sp,
    val large: TextUnit = 24.sp,
    val x_large: TextUnit = 26.sp,
    val xx_large: TextUnit = 28.sp,
    val xxx_large: TextUnit = 30.sp,
)