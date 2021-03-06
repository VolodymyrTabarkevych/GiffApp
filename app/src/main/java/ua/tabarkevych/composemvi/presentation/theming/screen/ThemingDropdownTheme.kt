package ua.tabarkevych.composemvi.presentation.theming.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import ua.tabarkevych.composemvi.ui.dimens.LocalSpacing
import ua.tabarkevych.composemvi.ui.theme.AppTheme

@Preview
@Composable
fun ThemingDropdownThemePreview() {
    ThemingDropdownTheme(state = ThemingContract.State()) { }
}

@Composable
fun ThemingDropdownTheme(
    state: ThemingContract.State,
    setEvent: (event: ThemingContract.Event) -> Unit
) {
    Column(modifier = Modifier.padding(top = LocalSpacing.current.medium)) {
        var textFieldSize by remember { mutableStateOf(Size.Zero) }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() }
                .noRippleClickable {
                    setEvent(
                        ThemingContract.Event.OnThemesExpandedStateChanged(
                            !state.isThemeExpanded
                        )
                    )
                },
            enabled = false,
            value = state.selectedTheme.name,
            onValueChange = { },
            label = { Text(stringResource(id = R.string.theming_theme_label)) },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = MaterialTheme.colors.onBackground,
                disabledBorderColor = MaterialTheme.colors.onBackground,
                disabledLabelColor = MaterialTheme.colors.onBackground,
                disabledPlaceholderColor = MaterialTheme.colors.onBackground,
                disabledTrailingIconColor = MaterialTheme.colors.onBackground
            )
        )

        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            expanded = state.isThemeExpanded,
            onDismissRequest = {
                setEvent(ThemingContract.Event.OnThemesExpandedStateChanged(false))
            }
        ) {
            AppTheme.getAll().forEach { appTheme ->
                DropdownMenuItem(onClick = {
                    setEvent(ThemingContract.Event.OnThemeSelected(appTheme))
                }) {
                    Text(text = appTheme.name)
                }
            }
        }
    }
}