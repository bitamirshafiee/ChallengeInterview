package com.bitamirshafiee.challengeinterview.repository

import com.bitamirshafiee.challengeinterview.data.SearchResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class SearchUseCase {

    abstract fun search(tag: String, page: Int, pageSize: Int): Observable<SearchResponse>
    fun <T> addExecutors(observable: Observable<T>): Observable<T> =
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())


}

class SearchUseCaseImpl(private val stackOverFLowApi: StackOverflowApi) : SearchUseCase() {

    override fun search(
        tag: String,
        page: Int,
        pageSize: Int
    ): Observable<SearchResponse> {

        return addExecutors(stackOverFLowApi.search(tag, page, pageSize))


    }


}