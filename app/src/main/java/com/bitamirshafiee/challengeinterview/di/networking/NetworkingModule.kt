package com.bitamirshafiee.challengeinterview.di.networking

import com.bitamirshafiee.challengeinterview.common.Consts.baseUrlProvider
import com.bitamirshafiee.challengeinterview.repository.RequestHeaderInterceptor
import com.bitamirshafiee.challengeinterview.repository.SearchUseCase
import com.bitamirshafiee.challengeinterview.repository.SearchUseCaseImpl
import com.bitamirshafiee.challengeinterview.repository.StackOverflowApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkingModule {

    @Provides
    @HeaderInterceptor
    fun provideHeaderInterceptor(): Interceptor = RequestHeaderInterceptor()

    @Provides
    @BodyLogging
    fun provideBodyLogging(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @NetworkScope
    fun provideOkHttp(
         /*@HeaderInterceptor requestHeaderInterceptor: Interceptor,*/
         @BodyLogging httpLoggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            /*.addInterceptor(requestHeaderInterceptor)*/
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @NetworkScope
    fun provideRetrofit(client: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(baseUrlProvider)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

    @Provides
    @NetworkScope
    fun provideStackOverflowApi(retrofit: Retrofit): StackOverflowApi =
        retrofit.create(StackOverflowApi::class.java)

    @Provides
    @NetworkScope
    fun provideSearchUseCase(stackOverflowApi: StackOverflowApi): SearchUseCase =
        SearchUseCaseImpl(stackOverflowApi)


}