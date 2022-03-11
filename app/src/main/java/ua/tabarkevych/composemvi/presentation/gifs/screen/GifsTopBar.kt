package ua.tabarkevych.composemvi.presentation.gifs.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.presentation.gifs.GifsContract

@Composable
fun GifsTopBar(setEvent: (event: GifsContract.Event) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Gifs"
            )
        },
        actions = {
            IconButton(onClick = { setEvent(GifsContract.Event.OnToolbarThemingClicked) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_palette_24),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}

@Composable
@Preview
fun GifsTopBarPreview() {
    GifsTopBar {}
}