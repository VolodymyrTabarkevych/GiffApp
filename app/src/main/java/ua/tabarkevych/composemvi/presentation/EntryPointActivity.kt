package ua.tabarkevych.composemvi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint
import ua.tabarkevych.composemvi.navigation.SetupNavGraph
import ua.tabarkevych.composemvi.ui.theme.ComposemviTheme

@ExperimentalCoilApi
@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    private val viewModel: EntryPointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ComposemviTheme(viewModel.viewState.collectAsState().value) {
                App()
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun App() {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}