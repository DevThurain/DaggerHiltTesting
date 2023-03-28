package com.thurainx.androiddaggerhilttesting.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiBuilder {

    fun buildRetrofitApi() : AppApi{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .authenticator(TokenAuthenticator())
            .build()


        val retrofitClient = Retrofit.Builder()
            .baseUrl(ApiConstants.BASED_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        return retrofitClient.create(AppApi::class.java)
    }

    fun buildTokenRefreshApi() : TokenRefreshApi{
        val retrofitClient = Retrofit.Builder()
            .baseUrl(ApiConstants.BASED_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofitClient.create(TokenRefreshApi::class.java)
    }
}