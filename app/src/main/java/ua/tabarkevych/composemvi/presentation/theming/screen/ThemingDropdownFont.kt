package ua.tabarkevych.composemvi.presentation.theming.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import ua.tabarkevych.composemvi.R
import ua.tabarkevych.composemvi.extensions.noRippleClickable
import ua.tabarkevych.composemvi.presentation.theming.ThemingContract
import ua.tabarkevych.composemvi.ui.theme.AppFont

@Preview
@Composable
fun ThemingDropdownFontPreview() {
    ThemingDropdownFont(
        state = ThemingContract.State(isFontExpanded = false, AppFont.Smooch)
    ) {}
}

@Composable
fun ThemingDropdownFont(
    state: ThemingContract.State,
    setEvent: (event: ThemingContract.Event) -> Unit
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Column() {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() }
                .noRippleClickable {
                    setEvent(
                        ThemingContract.Event.OnFontsExpandedStateChanged(
                            !state.isFontExpanded
                        )
                    )
                },
            enabled = false,
            value = state.selectedFont.name,
            onValueChange = {},
            label = {
                Text(
                    text = stringResource(id = R.string.theming_font_label),
                    fontFamily = state.selectedFont.family
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors()
        )

        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            expanded = state.isFontExpanded,
            onDismissRequest = {
                setEvent(
                    ThemingContract.Event.OnFontsExpandedStateChanged(false)
                )
            }
        ) {
            AppFont.getAll().forEach { font ->
                DropdownMenuItem(onClick = {
                    setEvent(ThemingContract.Event.OnFontSelected(font))
                }) {
                    Text(text = font.name)
                }
            }
        }
    }
}