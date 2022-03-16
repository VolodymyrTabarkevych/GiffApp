package ua.tabarkevych.composemvi.presentation.gifs

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.tabarkevych.composemvi.domain.model.GifPost
import ua.tabarkevych.composemvi.presentation.base.ViewEffect
import ua.tabarkevych.composemvi.presentation.base.ViewEvent
import ua.tabarkevych.composemvi.presentation.base.ViewState

class GifsContract {

    sealed class Event : ViewEvent {

        object OnToolbarThemingClicked : Event()

        data class OnGifClicked(val gifUrl: String) : Event()

        data class OnGifsError(val message: String) : Event()
    }

    data class State(val gifsPagingDataFlow: Flow<PagingData<GifPost>>) : ViewState

    sealed class Effect : ViewEffect {

        data class ShowError(val message: String) : Effect()

        sealed class Navigation : Effect() {

            object ToTheming : Navigation()

            data class ToGif(val gifUrl: String) : Navigation()
        }
    }
}