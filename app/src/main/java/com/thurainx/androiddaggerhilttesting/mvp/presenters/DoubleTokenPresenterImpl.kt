package com.thurainx.androiddaggerhilttesting.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import com.thurainx.androiddaggerhilttesting.model.DaggerHiltTestingModelImpl
import com.thurainx.androiddaggerhilttesting.mvp.views.DoubleTokenView
import kotlinx.coroutines.launch

class DoubleTokenPresenterImpl : AbstractBasedPresenter<DoubleTokenView, DoubleTokenPresenter>(),
    DoubleTokenPresenter {

    private val mDaggerHiltTestingModel = DaggerHiltTestingModelImpl()

    override fun onUiReady(context: Context, owner: LifecycleOwner) {

    }

    override fun showErrorMessage(message: String) {

    }

    override fun onTapHealthCheck() {
        mView.onLoadingHealthCheck()
        viewModelScope.launch {
            val respond = mDaggerHiltTestingModel.getApiHealthStatus()
            if(respond.isSuccessful){
                mView.onSuccessHealthCheck()
            }
        }
    }

    override fun onTapAuthorizeCheck() {
        mView.onLoadingAuthorizeCheck()
        viewModelScope.launch {
            val respond = mDaggerHiltTestingModel.login()
            if(respond.isSuccessful){
                mView.onSuccessAuthorizeCheck()
            }else{
                mView.showErrorMessage(respond.code().toString())
            }
        }
    }
}