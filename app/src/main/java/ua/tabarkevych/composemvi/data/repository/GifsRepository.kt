package ua.tabarkevych.composemvi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import ua.tabarkevych.composemvi.data.paging.GifsPostSource
import ua.tabarkevych.composemvi.domain.repository.IGifsRepository
import ua.tabarkevych.composemvi.remote.datasource.gifs.IGifsRemoteDataSource
import javax.inject.Inject

class GifsRepository @Inject constructor(
    private val gifsRemoteDataSource: IGifsRemoteDataSource
) : IGifsRepository {

    override fun getTrendingGifs() = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        )
    ) { GifsPostSource(gifsRemoteDataSource = gifsRemoteDataSource) }.flow
}