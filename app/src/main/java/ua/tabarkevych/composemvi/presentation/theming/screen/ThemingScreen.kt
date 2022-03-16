package ua.tabarkevych.composemvi.presentation.theming.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ThemingDropdownFont(state = state, setEvent = setEvent)
                ThemingDropdownTheme(state = state, setEvent = setEvent)
                Row(modifier = Modifier.padding(top = MaterialTheme.spacing.medium)) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.font_h_1),
                            style = MaterialTheme.typography.h1
                        )
                        Text(
                            text = stringResource(id = R.string.font_h_2),
                            style = MaterialTheme.typography.h2
                        )
                        Text(
                            text = stringResource(id = R.string.font_h_3),
                            style = MaterialTheme.typography.h3
                        )
                        Text(
                            text = stringResource(id = R.string.font_h_4),
                            style = MaterialTheme.typography.h4
                        )
                        Text(
                            text = stringResource(id = R.string.font_h_5),
                            style = MaterialTheme.typography.h5
                        )
                        Text(
                            text = stringResource(id = R.string.font_h_6),
                            style = MaterialTheme.typography.h6
                        )
                    }
                    Row {
                        Column {
                            Text(
                                text = stringResource(id = R.string.font_body_1),
                                style = MaterialTheme.typography.body1
                            )
                            Text(
                                text = stringResource(id = R.string.font_body_2),
                                style = MaterialTheme.typography.body2
                            )
                            Text(
                                text = stringResource(id = R.string.font_button),
                                style = MaterialTheme.typography.button
                            )
                            Text(
                                text = stringResource(id = R.string.font_caption),
                                style = MaterialTheme.typography.caption
                            )
                            Text(
                                text = stringResource(id = R.string.font_overline),
                                style = MaterialTheme.typography.overline
                            )
                            Text(
                                text = stringResource(id = R.string.font_subtitle_1),
                                style = MaterialTheme.typography.subtitle1
                            )
                            Text(
                                text = stringResource(id = R.string.font_subtitle_2),
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                    }
                    Image(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                        painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                }
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