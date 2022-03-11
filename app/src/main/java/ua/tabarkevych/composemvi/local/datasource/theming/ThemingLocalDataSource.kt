package ua.tabarkevych.composemvi.local.datasource.theming

import kotlinx.coroutines.flow.Flow
import ua.tabarkevych.composemvi.local.preferences.theming.IThemingPreferences
import ua.tabarkevych.composemvi.ui.theme.AppFont
import ua.tabarkevych.composemvi.ui.theme.AppTheme
import javax.inject.Inject

class ThemingLocalDataSource @Inject constructor(
    private val preferences: IThemingPreferences
) : IThemingLocalDataSource {

    override suspend fun setAppFont(appFont: AppFont) {
        preferences.setAppFont(appFont)
    }

    override suspend fun appFontFlow(): Flow<AppFont> {
        return preferences.appFontFlow()
    }

    override suspend fun setAppTheme(appTheme: AppTheme) {
        preferences.setAppTheme(appTheme)
    }

    override suspend fun appThemeFlow(): Flow<AppTheme> {
        return preferences.appThemeFlow()
    }
}