package ua.tabarkevych.composemvi.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
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
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            }
        ) {
            val viewModel: GifsViewModel = hiltViewModel()
            GifsScreen(
                navController = navController,
                state = viewModel.viewState.collectAsState().value,
                effect = viewModel.effect,
                setEvent = { viewModel.setEvent(it) }
            )
        }
        composable(
            route = Screen.Theming.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            }
        ) {
            val viewModel: ThemingViewModel = hiltViewModel()
            ThemingScreen(
                navController = navController,
                state = viewModel.viewState.collectAsState().value,
                effect = viewModel.effect,
                setEvent = { viewModel.setEvent(it) }
            )
        }
        composable(
            route = Screen.Gif.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
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
                    setEvent = { viewModel.setEvent(it) },
                    gifUrl = gifUrl
                )
            } ?: throw IllegalArgumentException()
        }
    }
}