package com.thurainx.androiddaggerhilttesting.network.response

import com.google.gson.annotations.SerializedName

data class TokenResult(@SerializedName("access_token") val accessToken: String,@SerializedName("refresh_token") val refreshToken: String)
