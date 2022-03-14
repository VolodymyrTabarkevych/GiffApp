package ua.tabarkevych.composemvi.presentation.gifs.screen

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.presentation.gifs.GifsContract

@Composable
@Preview
fun GifsTopBarPreview() {
    GifsTopBar {}
}

@Composable
fun GifsTopBar(setEvent: (event: GifsContract.Event) -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.gifs_title)) },
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