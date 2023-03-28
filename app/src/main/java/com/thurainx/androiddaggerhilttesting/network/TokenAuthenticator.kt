package com.thurainx.androiddaggerhilttesting.network

import android.util.Log
import com.thurainx.androiddaggerhilttesting.model.DaggerHiltTestingModelImpl
import com.thurainx.androiddaggerhilttesting.network.response.TokenResult
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val result = refreshToken()
            Log.d("result",result.body().toString())
            if(result.isSuccessful){
                return@runBlocking response.request.newBuilder()
                    .url("https://1044f083-8562-4c64-be84-8919acf4d43d.mock.pstmn.io/health")
                    .build()
            }else{
               return@runBlocking null
            }
        }
    }

    private suspend fun refreshToken() : retrofit2.Response<TokenResult>{
       return DaggerHiltTestingModelImpl().refreshToken()
    }
}