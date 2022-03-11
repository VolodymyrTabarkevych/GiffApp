package ua.tabarkevych.composemvi.data.repository

import kotlinx.coroutines.flow.Flow
import ua.tabarkevych.composemvi.domain.repository.IThemingRepository
import ua.tabarkevych.composemvi.local.datasource.theming.IThemingLocalDataSource
import ua.tabarkevych.composemvi.ui.theme.AppFont
import ua.tabarkevych.composemvi.ui.theme.AppTheme
import javax.inject.Inject

class ThemingRepository @Inject constructor(
    private val localDataStore: IThemingLocalDataSource
) : IThemingRepository {

    override suspend fun setAppFont(appFont: AppFont) {
        localDataStore.setAppFont(appFont)
    }

    override suspend fun appFontFlow(): Flow<AppFont> {
        return localDataStore.appFontFlow()
    }

    override suspend fun setAppTheme(appTheme: AppTheme) {
        localDataStore.setAppTheme(appTheme)
    }

    override suspend fun appThemeFlow(): Flow<AppTheme> {
        return localDataStore.appThemeFlow()
    }
}