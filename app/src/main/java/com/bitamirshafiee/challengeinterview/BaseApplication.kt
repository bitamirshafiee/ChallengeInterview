package com.bitamirshafiee.challengeinterview

import android.app.Application
import androidx.multidex.MultiDex

class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
    }

}