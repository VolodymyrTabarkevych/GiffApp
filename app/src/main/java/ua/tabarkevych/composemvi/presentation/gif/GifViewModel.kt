package ua.tabarkevych.composemvi.presentation.gif

import dagger.hilt.android.lifecycle.HiltViewModel
import ua.tabarkevych.composemvi.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor() :
    BaseViewModel<GifContract.Event, GifContract.State, GifContract.Effect>() {

    override fun setInitialState(): GifContract.State {
        return GifContract.State
    }

    override fun handleEvent(event: GifContract.Event) {
        when (event) {
            GifContract.Event.OnToolbarBackNavigationClicked -> setEffect { GifContract.Effect.Navigation.Back }
        }
    }
}