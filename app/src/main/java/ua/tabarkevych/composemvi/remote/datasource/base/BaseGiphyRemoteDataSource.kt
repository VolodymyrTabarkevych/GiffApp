package ua.tabarkevych.composemvi.remote.datasource.base

import ua.tabarkevych.composemvi.remote.rest.IRestBuilder

abstract class BaseGiphyRemoteDataSource<T>(apiBuilder: IRestBuilder) :
    BaseRemoteDataSource<T>(apiBuilder) {

    override val hostUrl: String
        get() = API_URL

    companion object {

        private const val API_URL = "https://api.giphy.com/v1/gifs/"
    }
}