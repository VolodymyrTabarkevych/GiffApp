package ua.tabarkevych.composemvi.presentation.gif.screen

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.presentation.gif.GifContract
import ua.tabarkevych.composemvi.util.EMPTY_STRING


@ExperimentalCoilApi
@Preview
@Composable
private fun GifScreenPreview() {
    GifScreen(
        navController = rememberNavController(),
        state = GifContract.State,
        effect = emptyFlow(),
        setEvent = {},
        gifUrl = EMPTY_STRING
    )
}


@ExperimentalCoilApi
@Composable
fun GifScreen(
    navController: NavHostController,
    state: GifContract.State,
    effect: Flow<GifContract.Effect>,
    setEvent: (event: GifContract.Event) -> Unit,
    gifUrl: String
) {
    HandleEffect(navController = navController, effect = effect)
    Scaffold(
        topBar = { GifTopBar(setEvent = setEvent) },
        content = {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = rememberImagePainter(
                    data = gifUrl,
                    imageLoader = ImageLoader.Builder(LocalContext.current)
                        .placeholder(R.drawable.ic_baseline_gif_24)
                        .crossfade(true)
                        .componentRegistry {
                            if (Build.VERSION.SDK_INT >= 28) {
                                add(ImageDecoderDecoder(LocalContext.current))
                            } else {
                                add(GifDecoder())
                            }
                        }
                        .build()),
                contentDescription = null
            )
        }
    )
}

@Composable
private fun HandleEffect(navController: NavHostController, effect: Flow<GifContract.Effect>) {
    LaunchedEffect(effect) {
        effect.collectLatest { effect ->
            when (effect) {
                GifContract.Effect.Navigation.Back -> navController.popBackStack()
            }
        }
    }
}