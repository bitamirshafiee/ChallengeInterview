package com.bitamirshafiee.challengeinterview.di.search

import com.bitamirshafiee.challengeinterview.di.networking.NetworkScope
import com.bitamirshafiee.challengeinterview.repository.SearchUseCase
import com.bitamirshafiee.challengeinterview.questionlist.QuestionListViewModelImpl
import com.bitamirshafiee.challengeinterview.repository.SearchUseCaseImpl
import com.bitamirshafiee.challengeinterview.repository.StackOverflowApi
import dagger.Module
import dagger.Provides


@Module
class SearchModule {

    @SearchScope
    @Provides
    fun provideSearchViewModelFactory(searchUseCase: SearchUseCase):
            QuestionListViewModelImpl.Factory =
        QuestionListViewModelImpl.Factory(searchUseCase)

    @Provides
    @SearchScope
    fun provideSearchUseCase(stackOverflowApi: StackOverflowApi): SearchUseCase =
        SearchUseCaseImpl(stackOverflowApi)
}