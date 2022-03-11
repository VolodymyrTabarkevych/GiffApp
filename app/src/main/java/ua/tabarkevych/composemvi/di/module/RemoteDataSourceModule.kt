package ua.tabarkevych.composemvi.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.tabarkevych.composemvi.remote.datasource.gifs.GifsRemoteDataSource
import ua.tabarkevych.composemvi.remote.datasource.gifs.IGifsRemoteDataSource

@InstallIn(SingletonComponent::class)
@Module
interface RemoteDataSourceModule {

    @Binds
    fun bindGifsRemoteDataSource(source: GifsRemoteDataSource): IGifsRemoteDataSource
}