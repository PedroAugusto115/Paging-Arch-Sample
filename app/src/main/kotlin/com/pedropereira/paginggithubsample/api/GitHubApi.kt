package com.pedropereira.paginggithubsample.api

import android.util.Log
import com.pedropereira.paginggithubsample.model.Page
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories")
    fun getRepositoryPage(
            @Query("q") language: String = "language:Java",
            @Query("sort") sort: String = "stars",
            @Query("page") page: Int = 1,
            @Query("per_page") itemsCount: Int): Call<Page>

    companion object {
        private const val BASE_URL = "https://api.github.com/"

        fun create(): GitHubApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                    .baseUrl(HttpUrl.parse(BASE_URL)!!)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
                    .create(GitHubApi::class.java)
        }
    }
}