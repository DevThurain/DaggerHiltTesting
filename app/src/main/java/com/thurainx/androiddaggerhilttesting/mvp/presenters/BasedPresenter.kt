package com.thurainx.androiddaggerhilttesting.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.thurainx.androiddaggerhilttesting.mvp.views.BasedView

interface BasedPresenter<T> : BasedView {
    fun initView(view: T)
    fun onUiReady(context: Context, owner: LifecycleOwner)
}