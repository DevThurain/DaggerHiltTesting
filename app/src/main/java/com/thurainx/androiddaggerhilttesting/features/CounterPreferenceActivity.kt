package com.thurainx.androiddaggerhilttesting.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thurainx.androiddaggerhilttesting.R
import com.thurainx.androiddaggerhilttesting.databinding.ActivityCounterPreferenceBinding
import com.thurainx.androiddaggerhilttesting.utils.SharedPreferenceUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CounterPreferenceActivity : AppCompatActivity() {
    @Inject
    lateinit var mPref: SharedPreferenceUtils

    lateinit var binding: ActivityCounterPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setUpListeners()
    }

//    private fun setUpListeners(){
//        binding.tvCounter.text = mPref.getInt("count").toString()
//        binding.fabCounterAdd.setOnClickListener {
//            val count = mPref.getInt("count")
//            mPref.saveInt("count",count + 1)
//
//            val count2 = mPref.getInt("count")
//            binding.tvCounter.text = count2.toString()
//        }
//    }
}