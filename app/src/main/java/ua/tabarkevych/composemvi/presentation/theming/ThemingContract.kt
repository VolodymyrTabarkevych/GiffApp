package ua.tabarkevych.composemvi.presentation.theming

import ua.tabarkevych.composemvi.presentation.base.ViewEffect
import ua.tabarkevych.composemvi.presentation.base.ViewEvent
import ua.tabarkevych.composemvi.presentation.base.ViewState
import ua.tabarkevych.composemvi.ui.theme.AppFont
import ua.tabarkevych.composemvi.ui.theme.AppTheme

class ThemingContract {

    sealed class Event : ViewEvent {

        object OnToolbarBackNavigationClicked : Event()

        data class OnFontsExpandedStateChanged(val isExpanded: Boolean) : Event()

        data class OnFontSelected(val font: AppFont) : Event()

        data class OnThemesExpandedStateChanged(val isExpanded: Boolean) : Event()

        data class OnThemeSelected(val theme: AppTheme) : Event()
    }

    data class State(
        val isFontExpanded: Boolean = false,
        val selectedFont: AppFont = AppFont.Default,
        val isThemeExpanded: Boolean = false,
        val selectedTheme: AppTheme = AppTheme.Light
    ) : ViewState

    sealed class Effect : ViewEffect {

        sealed class Navigation : Effect() {

            object Back : Navigation()
        }
    }
}