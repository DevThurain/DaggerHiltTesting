package com.thurainx.androiddaggerhilttesting.mvp.presenters

import com.thurainx.androiddaggerhilttesting.mvp.views.BasedView
import com.thurainx.androiddaggerhilttesting.mvp.views.DoubleTokenView

interface DoubleTokenPresenter : BasedPresenter<DoubleTokenView>{
    fun onTapHealthCheck()
    fun onTapAuthorizeCheck()
}