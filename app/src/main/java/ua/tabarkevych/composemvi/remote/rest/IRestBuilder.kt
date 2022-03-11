package ua.tabarkevych.composemvi.remote.rest

import retrofit2.Retrofit

interface IRestBuilder {

    fun build(baseUrl: String): Retrofit
}