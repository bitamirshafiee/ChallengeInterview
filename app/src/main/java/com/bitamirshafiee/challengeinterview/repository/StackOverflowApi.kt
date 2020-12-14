package com.bitamirshafiee.challengeinterview.repository

import com.bitamirshafiee.challengeinterview.data.SearchResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StackOverflowApi {

    @GET("/2.2/search/advanced?order=desc&sort=activity&site=stackoverflow")
    fun search(
        @Query("title") tag: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ) : Observable<SearchResponse>
}