package ua.tabarkevych.composemvi.remote.datasource.gifs

import retrofit2.http.GET
import retrofit2.http.Query
import ua.tabarkevych.composemvi.remote.datasource.base.BaseGiphyRemoteDataSource
import ua.tabarkevych.composemvi.remote.responsebody.TrendingResponseBody
import ua.tabarkevych.composemvi.remote.rest.IRestBuilder
import javax.inject.Inject

class GifsRemoteDataSource @Inject constructor(
    apiBuilder: IRestBuilder
) : BaseGiphyRemoteDataSource<GifsRemoteDataSource.API>(apiBuilder), IGifsRemoteDataSource {

    override val apiInterface: Class<API>
        get() = API::class.java

    override suspend fun getTrendingGifs(limit: Int, offset: Int): TrendingResponseBody =
        api.getTrendingGifs(
            apiKey = "CI3pQ3LqLLD8vh4rQANXAMW2dawsFHoz",
            limit = limit,
            offset = offset,
            randomId = "e826c9fc5c929e0d6c6d423841a282aa"
        )

    interface API {

        @GET("trending")
        suspend fun getTrendingGifs(
            @Query("api_key") apiKey: String,
            @Query("limit") limit: Int,
            @Query("offset") offset: Int,
            @Query("random_id") randomId: String
        ): TrendingResponseBody
    }
}