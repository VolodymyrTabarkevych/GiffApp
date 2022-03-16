package ua.tabarkevych.composemvi.presentation.gifs

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import ua.tabarkevych.composemvi.domain.repository.IGifsRepository
import ua.tabarkevych.composemvi.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class GifsViewModel @Inject constructor(
    private val gifsRepository: IGifsRepository
) : BaseViewModel<GifsContract.Event, GifsContract.State, GifsContract.Effect>() {

    init {
        setState {
            copy(
                gifsPagingDataFlow = gifsRepository.getTrendingGifs().cachedIn(viewModelScope)
            )
        }
    }

    override fun setInitialState(): GifsContract.State {
        return GifsContract.State(emptyFlow())
    }

    override fun handleEvent(event: GifsContract.Event) {
        when (event) {
            is GifsContract.Event.OnToolbarThemingClicked -> setEffect { GifsContract.Effect.Navigation.ToTheming }
            is GifsContract.Event.OnGifClicked -> setEffect {
                GifsContract.Effect.Navigation.ToGif(event.gifUrl)
            }
            is GifsContract.Event.OnGifsError -> setEffect { GifsContract.Effect.ShowError(event.message) }
        }
    }
}