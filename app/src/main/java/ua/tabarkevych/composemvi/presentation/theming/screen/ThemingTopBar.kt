package ua.tabarkevych.composemvi.presentation.theming.screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.presentation.theming.ThemingContract

@Composable
@Preview
fun ThemingTopBarPreview() {
    ThemingTopBar {}
}

@Composable
fun ThemingTopBar(setEvent: (event: ThemingContract.Event) -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.theming_title))
        },
        navigationIcon = {
            IconButton(onClick = { setEvent(ThemingContract.Event.OnToolbarBackNavigationClicked) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = null
                )
            }
        }
    )
}