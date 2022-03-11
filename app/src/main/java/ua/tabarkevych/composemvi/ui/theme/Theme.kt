package ua.tabarkevych.composemvi.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.tabarkevych.composemvi.presentation.EntryPointContract
import ua.tabarkevych.composemvi.ui.dimens.LocalSpacing
import ua.tabarkevych.composemvi.ui.dimens.LocalTextSize
import ua.tabarkevych.composemvi.ui.dimens.Spacing
import ua.tabarkevych.composemvi.ui.dimens.TextSize


@Composable
fun ComposemviTheme(
    state: EntryPointContract.State,
    content: @Composable() () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    when (state.appTheme) {
        AppTheme.Dark -> {
            systemUiController.setSystemBarsColor(color = Color.Black, darkIcons = false)
        }
        AppTheme.Green -> {
            systemUiController.setSystemBarsColor(color = Color.White, darkIcons = true)
        }
        AppTheme.Light -> {
            systemUiController.setSystemBarsColor(color = Color.White, darkIcons = true)
        }
    }
    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalTextSize provides TextSize()
    ) {
        MaterialTheme(
            colors = ColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}