package com.thurainx.androiddaggerhilttesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.thurainx.androiddaggerhilttesting.utils.SharedPreferenceUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var mPref: SharedPreferenceUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveAndRetrieveToken()
    }


    private fun saveAndRetrieveToken(){
        mPref.deleteSharedPreference()
        Log.d("goal_count", mPref.getInt("goal_count").toString())
    }
}