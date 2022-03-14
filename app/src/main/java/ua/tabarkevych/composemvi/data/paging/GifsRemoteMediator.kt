package ua.tabarkevych.composemvi.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import ua.tabarkevych.composemvi.data.entity.GifEntity

@ExperimentalPagingApi
class GifsRemoteMediator : RemoteMediator<Int, GifEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifEntity>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}