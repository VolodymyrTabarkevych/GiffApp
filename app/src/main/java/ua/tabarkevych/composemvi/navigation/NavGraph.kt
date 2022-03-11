package ua.tabarkevych.composemvi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import ua.tabarkevych.composemvi.presentation.gif.GifViewModel
import ua.tabarkevych.composemvi.presentation.gif.screen.GifScreen
import ua.tabarkevych.composemvi.presentation.gifs.GifsViewModel
import ua.tabarkevych.composemvi.presentation.gifs.screen.GifsScreen
import ua.tabarkevych.composemvi.presentation.theming.ThemingViewModel
import ua.tabarkevych.composemvi.presentation.theming.screen.ThemingScreen

@ExperimentalCoilApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Gifs.route
    ) {
        composable(route = Screen.Gifs.route) {
            val viewModel: GifsViewModel = hiltViewModel()
            GifsScreen(
                navController = navController,
                state = viewModel.viewState.collectAsState().value,
                effect = viewModel.effect,
                setEvent = { viewModel.setEvent(it) }
            )
        }
        composable(route = Screen.Theming.route) {
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