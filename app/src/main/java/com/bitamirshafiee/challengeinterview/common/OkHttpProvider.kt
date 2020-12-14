package com.bitamirshafiee.challengeinterview.common

import okhttp3.OkHttpClient


fun getOkHttp() = OkHttpClient()
    .newBuilder().build()