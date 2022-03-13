package ua.tabarkevych.composemvi.presentation.theming.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.presentation.theming.ThemingContract
import ua.tabarkevych.composemvi.ui.dimens.spacing

@Preview
@Composable
private fun ThemingScreenPreview() {
    ThemingScreen(
        navController = rememberNavController(),
        state = ThemingContract.State(),
        effect = emptyFlow(),
        setEvent = {}
    )
}

@Composable
fun ThemingScreen(
    navController: NavHostController,
    state: ThemingContract.State,
    effect: Flow<ThemingContract.Effect>,
    setEvent: (event: ThemingContract.Event) -> Unit
) {
    HandleEffect(navController = navController, effect = effect)
    Scaffold(
        topBar = {
            ThemingTopBar(setEvent = setEvent)
        },
        content = {
            Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
                ThemingDropdownFont(state = state, setEvent = setEvent)
                ThemingDropdownTheme(state = state, setEvent = setEvent)
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary)
                )
            }
        }
    )
}

@Composable
private fun HandleEffect(navController: NavHostController, effect: Flow<ThemingContract.Effect>) {
    LaunchedEffect(effect) {
        effect.collectLatest { effect ->
            when (effect) {
                is ThemingContract.Effect.Navigation.Back -> navController.popBackStack()
            }
        }
    }
}