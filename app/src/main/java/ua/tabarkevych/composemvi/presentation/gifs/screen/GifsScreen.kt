package ua.tabarkevych.composemvi.presentation.gifs.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import ua.tabarkevych.composemvi.navigation.Screen
import ua.tabarkevych.composemvi.presentation.gifs.GifsContract

@ExperimentalCoilApi
@Preview
@Composable
private fun GifsScreenPreview() {
    GifsScreen(
        navController = rememberNavController(),
        state = GifsContract.State(emptyFlow()),
        effect = emptyFlow(),
        setEvent = {}
    )
}

@ExperimentalCoilApi
@Composable
fun GifsScreen(
    navController: NavHostController,
    state: GifsContract.State,
    effect: Flow<GifsContract.Effect>,
    setEvent: (event: GifsContract.Event) -> Unit
) {
    HandleEffect(context = LocalContext.current, navController = navController, effect = effect)
    Scaffold(
        topBar = {
            GifsTopBar(setEvent = setEvent)
        },
        content = {
            GifsList(
                items = state.gifsPagingDataFlow.collectAsLazyPagingItems(),
                setEvent = setEvent
            )
        }
    )
}

@Composable
private fun HandleEffect(
    context: Context,
    navController: NavHostController,
    effect: Flow<GifsContract.Effect>
) {
    LaunchedEffect(effect) {
        effect.collectLatest { effect ->
            when (effect) {
                is GifsContract.Effect.Navigation.ToTheming -> navController.navigate(Screen.Theming.route)
                is GifsContract.Effect.Navigation.ToGif -> navController.navigate(
                    Screen.Gif.setUrl(
                        effect.gifUrl
                    )
                )
                is GifsContract.Effect.ShowError -> Toast.makeText(
                    context,
                    effect.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}