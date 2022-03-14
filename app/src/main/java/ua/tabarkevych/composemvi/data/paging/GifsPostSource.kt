package ua.tabarkevych.composemvi.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ua.tabarkevych.composemvi.data.mapper.toDomain
import ua.tabarkevych.composemvi.domain.model.GifPost
import ua.tabarkevych.composemvi.remote.datasource.gifs.IGifsRemoteDataSource

class GifsPostSource(
    private val gifsRemoteDataSource: IGifsRemoteDataSource
) : PagingSource<Int, GifPost>() {

    override fun getRefreshKey(state: PagingState<Int, GifPost>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifPost> {
        return try {
            val offset = params.key ?: 0
            val response = gifsRemoteDataSource.getTrendingGifs(params.loadSize, offset)
            val endOfPaginationReached = response.data.isEmpty()
            if (endOfPaginationReached) {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            } else {
                val gifs = response.data
                    .filter { it.user != null }
                    .map { it.toDomain() }

                LoadResult.Page(
                    data = gifs.toList(),
                    prevKey = if (offset == 0) null else offset - params.loadSize,
                    nextKey = offset + params.loadSize
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}