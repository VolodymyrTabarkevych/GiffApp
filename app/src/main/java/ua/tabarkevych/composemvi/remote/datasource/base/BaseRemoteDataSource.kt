package ua.tabarkevych.composemvi.remote.datasource.base

import ua.tabarkevych.composemvi.remote.rest.IRestBuilder

abstract class BaseRemoteDataSource<T>(private val apiBuilder: IRestBuilder) {

    protected abstract val hostUrl: String
    protected abstract val apiInterface: Class<T>

    private var currentHostUrl: String? = null
    private var currentApi: T? = null

    protected val api: T
        get() {
            val shouldRecreate = currentHostUrl != hostUrl || currentApi == null
            return (currentApi?.takeIf { !shouldRecreate } ?: createApi()).also {
                currentHostUrl = hostUrl
                currentApi = it
            }
        }

    private fun createApi(): T {
        return apiBuilder.build(hostUrl).create(apiInterface)
    }
}