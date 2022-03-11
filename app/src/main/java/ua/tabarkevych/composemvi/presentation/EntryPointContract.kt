package ua.tabarkevych.composemvi.presentation

import ua.tabarkevych.composemvi.presentation.base.ViewEffect
import ua.tabarkevych.composemvi.presentation.base.ViewEvent
import ua.tabarkevych.composemvi.presentation.base.ViewState
import ua.tabarkevych.composemvi.ui.theme.AppTheme

class EntryPointContract {

    sealed class Event : ViewEvent

    data class State(
        val appTheme: AppTheme
    ) : ViewState

    sealed class Effect : ViewEffect
}