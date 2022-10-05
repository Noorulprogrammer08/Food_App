package com.mallowtech.foodapp.ApiSetup

import com.mallowtech.foodapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        private const val KEY_CONTENT_TYPE = "content_type"
        private const val APPLICATION_JSON = "application/json"

        val unAuthorisedClient: FoodAppApiInterface by lazy {
            buildRetrofit(unAuthorisedOkHttpClient).create(FoodAppApiInterface::class.java)
        }
        private val unAuthorisedOkHttpClient by lazy {
            generateOkHttpClient()
        }

        private fun generateOkHttpClient(): OkHttpClient =
            OkHttpClient().newBuilder().apply {

                addInterceptor { chain ->
                    chain.proceed(getRequest(chain.request()))
                }
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(logging)
                }
            }.build()

        private fun getRequest(request: Request): Request =
            request.newBuilder().apply {
                header(KEY_CONTENT_TYPE, APPLICATION_JSON)
            }.build()

        private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
            baseUrl("https://api.spoonacular.com/")
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

    }
}