package ua.tabarkevych.composemvi.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.tabarkevych.composemvi.local.preferences.theming.IThemingPreferences
import ua.tabarkevych.composemvi.local.preferences.theming.ThemingPreferences

@InstallIn(SingletonComponent::class)
@Module
interface PreferencesModule {

    @Binds
    fun provideThemingPreferences(preferences: ThemingPreferences): IThemingPreferences
}