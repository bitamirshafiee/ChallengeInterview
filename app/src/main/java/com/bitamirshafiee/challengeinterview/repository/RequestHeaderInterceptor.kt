package com.bitamirshafiee.challengeinterview.repository

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        builder.addHeader("Jwt", "bita")
        val newRequest = builder.build()
        return chain.proceed(newRequest)
    }
}