package com.thurainx.androiddaggerhilttesting.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.thurainx.androiddaggerhilttesting.mvp.views.MainView
import com.thurainx.androiddaggerhilttesting.utils.SharedPreferenceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainPresenterImpl @Inject constructor(
    var mPref : SharedPreferenceUtils
) : AbstractBasedPresenter<MainView, MainPresenter>() {

    var isReady = false
    override fun onUiReady(context: Context, owner: LifecycleOwner) {
        viewModelScope.launch {
            delay(1000L)
            isReady = true
            mView.initializationComplete(mPref.getInt("count").toString())

        }
    }

    override fun showErrorMessage(message: String) {

    }


}