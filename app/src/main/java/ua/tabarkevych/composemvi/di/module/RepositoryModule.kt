package ua.tabarkevych.composemvi.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.tabarkevych.composemvi.data.repository.GifsRepository
import ua.tabarkevych.composemvi.data.repository.ThemingRepository
import ua.tabarkevych.composemvi.domain.repository.IGifsRepository
import ua.tabarkevych.composemvi.domain.repository.IThemingRepository

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindGifsRepository(repository: GifsRepository): IGifsRepository

    @Binds
    fun bindThemingRepository(repository: ThemingRepository): IThemingRepository
}