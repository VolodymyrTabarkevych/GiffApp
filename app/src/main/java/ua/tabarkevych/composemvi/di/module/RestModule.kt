package ua.tabarkevych.composemvi.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.tabarkevych.composemvi.remote.rest.IRestBuilder
import ua.tabarkevych.composemvi.remote.rest.RestBuilder

@InstallIn(SingletonComponent::class)
@Module
interface RestModule {

    @Binds
    fun bindNetworkModule(restBuilder: RestBuilder): IRestBuilder
}