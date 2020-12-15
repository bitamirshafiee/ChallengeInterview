package com.bitamirshafiee.challengeinterview.di.repository.module

import android.app.Application
import android.content.Context
import com.bitamirshafiee.challengeinterview.di.repository.scope.ApplicationScope
import com.bitamirshafiee.challengeinterview.di.repository.scope.NetworkScope
import com.bitamirshafiee.challengeinterview.repository.StackOverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApplicationModule(private val application : Application) {

    @Provides
    @NetworkScope
    fun provideStackOverflowApi(retrofit: Retrofit): StackOverflowApi =
        retrofit.create(StackOverflowApi::class.java)

    @Provides
    @ApplicationScope
    fun provideContext() : Context = application
}