package com.thurainx.androiddaggerhilttesting.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.thurainx.androiddaggerhilttesting.mvp.views.MainView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainPresenterImpl : AbstractBasedPresenter<MainView, MainPresenter>() {

    var isReady = false
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        viewModelScope.launch {
            delay(1000L)
            isReady = true
            mView.initializationComplete()

        }
    }

    override fun showErrorMessage(message: String) {

    }


}