package com.thurainx.androiddaggerhilttesting

import android.animation.ObjectAnimator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModel
import com.thurainx.androiddaggerhilttesting.mvp.presenters.MainPresenter
import com.thurainx.androiddaggerhilttesting.mvp.presenters.MainPresenterImpl
import com.thurainx.androiddaggerhilttesting.mvp.views.MainView
import com.thurainx.androiddaggerhilttesting.utils.SharedPreferenceUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView {
    @Inject
    lateinit var mPref: SharedPreferenceUtils

    private val mPresenter: MainPresenterImpl by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpViewModel()

        // Set up an OnPreDrawListener to the root view.
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.

                    return if (mPresenter.isReady) {

                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun setUpViewModel(){
        mPresenter.initView(this)
        mPresenter.onUiReady(this, this)
    }

    override fun initializationComplete() {

    }

    override fun showErrorMessage(message: String) {

    }
}