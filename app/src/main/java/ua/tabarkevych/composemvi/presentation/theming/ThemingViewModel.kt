package ua.tabarkevych.composemvi.presentation.theming

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ua.tabarkevych.composemvi.domain.repository.IThemingRepository
import ua.tabarkevych.composemvi.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ThemingViewModel @Inject constructor(
    private val themingRepository: IThemingRepository
) : BaseViewModel<ThemingContract.Event, ThemingContract.State, ThemingContract.Effect>() {

    init {
        subscribeOnFontChanges()
        subscribeOnThemeChanges()
    }

    private fun subscribeOnFontChanges() {
        viewModelScope.launch {
            themingRepository.appFontFlow().collectLatest {
                setState { copy(selectedFont = it, isFontExpanded = false) }
            }
        }
    }

    private fun subscribeOnThemeChanges() {
        viewModelScope.launch {
            themingRepository.appThemeFlow().collectLatest {
                setState { copy(selectedTheme = it, isThemeExpanded = false) }
            }
        }
    }

    override fun setInitialState(): ThemingContract.State {
        return ThemingContract.State()
    }

    override fun handleEvent(event: ThemingContract.Event) {
        viewModelScope.launch {
            when (event) {
                ThemingContract.Event.OnToolbarBackNavigationClicked -> {
                    setEffect { ThemingContract.Effect.Navigation.Back }
                }
                is ThemingContract.Event.OnFontSelected -> {
                    themingRepository.setAppFont(event.font)
                }
                is ThemingContract.Event.OnFontsExpandedStateChanged -> {
                    setState { copy(isFontExpanded = event.isExpanded) }
                }
                is ThemingContract.Event.OnThemeSelected -> {
                    themingRepository.setAppTheme(event.theme)
                }
                is ThemingContract.Event.OnThemesExpandedStateChanged -> {
                    setState { copy(isThemeExpanded = event.isExpanded) }
                }
            }
        }
    }
}