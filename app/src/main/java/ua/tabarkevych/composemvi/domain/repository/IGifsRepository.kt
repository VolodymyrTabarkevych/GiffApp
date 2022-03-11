package ua.tabarkevych.composemvi.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ua.tabarkevych.composemvi.domain.model.GifPost

interface IGifsRepository {

    fun getTrendingGifs(): Flow<PagingData<GifPost>>
}