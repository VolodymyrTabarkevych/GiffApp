package ua.tabarkevych.composemvi.local.preferences.theming

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.tabarkevych.composemvi.di.qualifire.ThemingPreferences
import ua.tabarkevych.composemvi.ui.theme.AppFont
import ua.tabarkevych.composemvi.ui.theme.AppTheme
import javax.inject.Inject

class ThemingPreferences @Inject constructor(
    @ThemingPreferences private val dataStore: DataStore<Preferences>
) : IThemingPreferences {

    override suspend fun setAppFont(appFont: AppFont) {
        dataStore.edit {
            it[APP_FONT_KEY] = appFont.name
        }
    }

    override suspend fun appFontFlow(): Flow<AppFont> = dataStore.data.map {
        val fontName = it[APP_FONT_KEY] ?: AppFont.Default.name
        //TODO
        AppFont.getAll().first { font -> font.name == fontName }
    }

    override suspend fun setAppTheme(appTheme: AppTheme) {
        dataStore.edit {
            it[APP_THEME_KEY] = appTheme.name
        }
    }

    override suspend fun appThemeFlow(): Flow<AppTheme> = dataStore.data.map {
        val appThemeName = it[APP_THEME_KEY] ?: AppTheme.Light.name
        //TODO
        AppTheme.getAll().first { appTheme -> appTheme.name == appThemeName }
    }

    companion object {

        private val APP_FONT_KEY = stringPreferencesKey("app_font")
        private val APP_THEME_KEY = stringPreferencesKey("app_theme")
    }
}