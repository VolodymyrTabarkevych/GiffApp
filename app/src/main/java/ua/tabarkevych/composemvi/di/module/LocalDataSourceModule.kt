package ua.tabarkevych.composemvi.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.tabarkevych.composemvi.local.datasource.theming.IThemingLocalDataSource
import ua.tabarkevych.composemvi.local.datasource.theming.ThemingLocalDataSource

@InstallIn(SingletonComponent::class)
@Module
interface LocalDataSourceModule {

    @Binds
    fun bindThemingLocalDataSource(source: ThemingLocalDataSource): IThemingLocalDataSource
}