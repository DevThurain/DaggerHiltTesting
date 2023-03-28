package com.thurainx.androiddaggerhilttesting.model

import com.thurainx.androiddaggerhilttesting.network.response.ApiHealthResult
import com.thurainx.androiddaggerhilttesting.network.response.LoginResult
import com.thurainx.androiddaggerhilttesting.network.response.TokenResult
import retrofit2.Response

interface DaggerHiltTestingModel {
    suspend fun getApiHealthStatus() : Response<ApiHealthResult>
    suspend fun login() : Response<LoginResult>
    suspend fun refreshToken() : Response<TokenResult>
}