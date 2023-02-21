package com.thurainx.androiddaggerhilttesting.mvp.presenters

import androidx.lifecycle.ViewModel
import com.thurainx.androiddaggerhilttesting.mvp.views.BasedView


abstract class AbstractBasedPresenter<T: BasedView , P: BasedPresenter<T>> : BasedPresenter<T>, ViewModel() {
    protected lateinit var mView: T

    override fun initView(view: T) {
        mView = view
    }

}