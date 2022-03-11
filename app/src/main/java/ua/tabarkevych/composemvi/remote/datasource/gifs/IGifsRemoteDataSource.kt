package ua.tabarkevych.composemvi.remote.datasource.gifs

import ua.tabarkevych.composemvi.remote.responsebody.TrendingResponseBody

interface IGifsRemoteDataSource {

    suspend fun getTrendingGifs(limit: Int, offset: Int): TrendingResponseBody
}