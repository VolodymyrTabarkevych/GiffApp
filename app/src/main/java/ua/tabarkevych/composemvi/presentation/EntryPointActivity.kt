package ua.tabarkevych.composemvi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import ua.tabarkevych.composemvi.navigation.SetupNavGraph
import ua.tabarkevych.composemvi.ui.theme.ComposemviTheme

@ExperimentalAnimationApi
@ExperimentalCoilApi
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    private val viewModel: EntryPointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val state = viewModel.viewState.collectAsState().value
            BoxWithConstraints() {
                ComposemviTheme(state) {
                    App(constraints.maxWidth / 2)
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
private fun App(width: Int) {
    val navController = rememberAnimatedNavController()
    SetupNavGraph(navController = navController, width)
}