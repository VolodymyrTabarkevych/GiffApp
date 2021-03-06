package ua.tabarkevych.composemvi.remote.rest

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RestBuilder @Inject constructor(@ApplicationContext val context: Context) : IRestBuilder {

    private val gson by lazy {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    override fun build(
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .build()

    private fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                request.addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE_HEADER_VALUE)
                chain.proceed(request.build())
            }
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .build()

    companion object {

        private const val CONNECTION_TIMEOUT = 10L
        private const val WRITE_TIMEOUT = 20L
        private const val READ_TIMEOUT = 30L

        const val CONTENT_TYPE_HEADER = "Content-Type"
        const val CONTENT_TYPE_HEADER_VALUE = "application/json"
    }
}