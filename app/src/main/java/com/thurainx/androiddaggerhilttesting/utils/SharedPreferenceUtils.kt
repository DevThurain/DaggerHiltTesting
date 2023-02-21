package com.thurainx.androiddaggerhilttesting.utils

import android.content.Context
import android.content.SharedPreferences

const val PREF_NAME = "TEMPORARY_DB"
class SharedPreferenceUtils(context: Context) {
    private var sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    fun saveString(key: String, value: String){
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String) : String{
        return sharedPref.getString(key, "") ?: ""
    }

    fun saveInt(key: String, value: Int){
        with (sharedPref.edit()) {
            putInt(key, value)
            apply()
        }
    }

    fun getInt(key: String) : Int{
        return sharedPref.getInt(key, 0)
    }

    fun deleteSharedPreference(){
        sharedPref.edit().clear().apply()
    }

}