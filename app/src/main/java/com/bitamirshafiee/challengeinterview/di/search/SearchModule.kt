package com.bitamirshafiee.challengeinterview.di.search

import com.bitamirshafiee.challengeinterview.repository.SearchUseCase
import com.bitamirshafiee.challengeinterview.questionlist.QuestionListViewModelImpl
import dagger.Module
import dagger.Provides


@Module
class SearchModule {

    @Provides
    @SearchScope
    fun provideSearchViewModelFactory(searchUseCase: SearchUseCase):
            QuestionListViewModelImpl.Factory =
        QuestionListViewModelImpl.Factory(searchUseCase)

}