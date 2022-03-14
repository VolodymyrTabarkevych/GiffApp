package ua.tabarkevych.composemvi.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.tabarkevych.composemvi.data.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = AppDatabase.getInstance(app)

    @Provides
    @Singleton
    fun provideGifDao(db: AppDatabase) = db.gifDao()
}