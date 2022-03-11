package ua.tabarkevych.composemvi.presentation.gif

import ua.tabarkevych.composemvi.presentation.base.ViewEffect
import ua.tabarkevych.composemvi.presentation.base.ViewEvent
import ua.tabarkevych.composemvi.presentation.base.ViewState

class GifContract {

    sealed class Event : ViewEvent {

        object OnToolbarBackNavigationClicked : Event()
    }

    object State : ViewState

    sealed class Effect : ViewEffect {

        sealed class Navigation : Effect() {

            object Back : Navigation()
        }
    }
}