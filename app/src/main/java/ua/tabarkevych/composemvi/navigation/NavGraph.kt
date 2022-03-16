package ua.tabarkevych.composemvi.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import ua.tabarkevych.composemvi.presentation.gif.GifViewModel
import ua.tabarkevych.composemvi.presentation.gif.screen.GifScreen
import ua.tabarkevych.composemvi.presentation.gifs.GifsViewModel
import ua.tabarkevych.composemvi.presentation.gifs.screen.GifsScreen
import ua.tabarkevych.composemvi.presentation.theming.ThemingViewModel
import ua.tabarkevych.composemvi.presentation.theming.screen.ThemingScreen

private const val TRANSITION_DURATION = 300

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun SetupNavGraph(navController: NavHostController, width: Int) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Gifs.route
    ) {
        composable(
            route = Screen.Gifs.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = TRANSITION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = TRANSITION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        ) {
            val viewModel: GifsViewModel = hiltViewModel()
            GifsScreen(
                navController = navController,
                state = viewModel.viewState.collectAsState().value,
                effect = viewModel.effect,
                setEvent = viewModel::setEvent
            )
        }
        composable(
            route = Screen.Theming.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = TRANSITION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = TRANSITION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        ) {
            val viewModel: ThemingViewModel = hiltViewModel()
            ThemingScreen(
                navController = navController,
                state = viewModel.viewState.collectAsState().value,
                effect = viewModel.effect,
                setEvent = viewModel::setEvent
            )
        }
        composable(
            route = Screen.Gif.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = TRANSITION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = TRANSITION_DURATION,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            arguments = listOf(navArgument(Screen.Gif.Args.GIF_URL) {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val viewModel: GifViewModel = hiltViewModel()
            navBackStackEntry.arguments?.getString(Screen.Gif.Args.GIF_URL)?.let { gifUrl ->
                GifScreen(
                    navController = navController,
                    state = viewModel.viewState.collectAsState().value,
                    effect = viewModel.effect,
                    setEvent = viewModel::setEvent,
                    gifUrl = gifUrl
                )
            } ?: throw IllegalArgumentException()
        }
    }
}