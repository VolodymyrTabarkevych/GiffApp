package ua.tabarkevych.composemvi.ui.dimens

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalWidth = compositionLocalOf { Width() }

val MaterialTheme.width: Width
    @Composable
    @ReadOnlyComposable
    get() = LocalWidth.current

data class Width(
    val default: Dp = 0.dp,
    val small: Dp = 1.dp,
    val medium: Dp = 2.dp,
    val large: Dp = 3.dp
)