package com.thurainx.androiddaggerhilttesting.di

import android.content.Context
import com.thurainx.androiddaggerhilttesting.utils.SharedPreferenceUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext appContext: Context
    ) : SharedPreferenceUtils = SharedPreferenceUtils(appContext)
}