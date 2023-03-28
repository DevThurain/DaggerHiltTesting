package com.thurainx.androiddaggerhilttesting.mvp.views

interface DoubleTokenView : BasedView{
    fun onLoadingHealthCheck()
    fun onLoadingAuthorizeCheck()

    fun onSuccessHealthCheck()
    fun onSuccessAuthorizeCheck()
}