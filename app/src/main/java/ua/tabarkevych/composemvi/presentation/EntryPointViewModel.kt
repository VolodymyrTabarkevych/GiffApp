package ua.tabarkevych.composemvi.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.tabarkevych.composemvi.domain.repository.IThemingRepository
import ua.tabarkevych.composemvi.presentation.base.BaseViewModel
import ua.tabarkevych.composemvi.ui.theme.AppTheme
import ua.tabarkevych.composemvi.ui.theme.setColorPalette
import ua.tabarkevych.composemvi.ui.theme.setTypography
import javax.inject.Inject

@HiltViewModel
class EntryPointViewModel @Inject constructor(
    private val themingRepository: IThemingRepository
) : BaseViewModel<EntryPointContract.Event, EntryPointContract.State, EntryPointContract.Effect>() {

    init {
        subscribeOnFontChanges()
        subscribeOnThemeChanges()
    }

    private fun subscribeOnFontChanges() {
        viewModelScope.launch {
            themingRepository.appFontFlow().collectLatest {
                //TODO
                setTypography(it)
            }
        }
    }

    private fun subscribeOnThemeChanges() {
        viewModelScope.launch {
            themingRepository.appThemeFlow().collectLatest {
                //TODO
                setColorPalette(it)
                setState { copy(appTheme = it) }
            }
        }
    }

    override fun setInitialState(): EntryPointContract.State {
        return EntryPointContract.State(AppTheme.Light)
    }

    override fun handleEvent(event: EntryPointContract.Event) {
    }
}