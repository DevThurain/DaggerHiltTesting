package com.thurainx.androiddaggerhilttesting.network

import com.thurainx.androiddaggerhilttesting.network.response.ApiHealthResult
import com.thurainx.androiddaggerhilttesting.network.response.LoginResult
import com.thurainx.androiddaggerhilttesting.network.response.TokenResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface AppApi {

    @GET("/health")
    suspend fun checkApiHealth() : Response<ApiHealthResult>

    @GET("/refresh")
    suspend fun refreshToken(): Response<TokenResult>

    @GET("/login")
    suspend fun login(): Response<LoginResult>
}