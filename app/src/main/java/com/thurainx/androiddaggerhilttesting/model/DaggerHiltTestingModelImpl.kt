package com.thurainx.androiddaggerhilttesting.model

import com.thurainx.androiddaggerhilttesting.network.ApiBuilder
import com.thurainx.androiddaggerhilttesting.network.ApiConstants
import com.thurainx.androiddaggerhilttesting.network.AppApi
import com.thurainx.androiddaggerhilttesting.network.TokenRefreshApi
import com.thurainx.androiddaggerhilttesting.network.response.ApiHealthResult
import com.thurainx.androiddaggerhilttesting.network.response.LoginResult
import com.thurainx.androiddaggerhilttesting.network.response.TokenResult
import retrofit2.Response

class DaggerHiltTestingModelImpl : DaggerHiltTestingModel {
    private val mAppApi : AppApi = ApiBuilder.buildRetrofitApi()
    private val mRefreshApi: TokenRefreshApi = ApiBuilder.buildTokenRefreshApi()

    override suspend fun getApiHealthStatus(): Response<ApiHealthResult> {
        return mAppApi.checkApiHealth()
    }

    override suspend fun login(): Response<LoginResult> {
        return mAppApi.login()
    }

    override suspend fun refreshToken(): Response<TokenResult> {
       return mRefreshApi.refreshToken()
    }
}