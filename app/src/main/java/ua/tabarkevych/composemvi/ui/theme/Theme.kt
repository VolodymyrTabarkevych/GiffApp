package ua.tabarkevych.composemvi.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ua.tabarkevych.composemvi.presentation.EntryPointContract
import ua.tabarkevych.composemvi.ui.dimens.*


@Composable
fun ComposemviTheme(
    state: EntryPointContract.State,
    content: @Composable() () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    when (state.appTheme) {
        AppTheme.Dark -> {
            systemUiController.setSystemBarsColor(
                color = AppTheme.Dark.colorPalette.primary,
                darkIcons = false
            )
        }
        AppTheme.Green -> {
            systemUiController.setSystemBarsColor(
                color = AppTheme.Green.colorPalette.primary,
                darkIcons = true
            )
        }
        AppTheme.Light -> {
            systemUiController.setSystemBarsColor(
                color = AppTheme.Light.colorPalette.primary,
                darkIcons = true
            )
        }
    }
    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalTextSize provides TextSize(),
        LocalWidth provides Width(),
        LocalElevation provides Elevation()
    ) {
        MaterialTheme(
            colors = ColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}