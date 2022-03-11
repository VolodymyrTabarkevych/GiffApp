package ua.tabarkevych.composemvi.local.datasource.theming

import kotlinx.coroutines.flow.Flow
import ua.tabarkevych.composemvi.ui.theme.AppFont
import ua.tabarkevych.composemvi.ui.theme.AppTheme

interface IThemingLocalDataSource {

    suspend fun setAppFont(appFont: AppFont)

    suspend fun appFontFlow(): Flow<AppFont>

    suspend fun setAppTheme(appTheme: AppTheme)

    suspend fun appThemeFlow(): Flow<AppTheme>
}