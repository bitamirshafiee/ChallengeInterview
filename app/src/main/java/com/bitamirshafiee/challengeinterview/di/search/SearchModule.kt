package com.bitamirshafiee.challengeinterview.di.search

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.bitamirshafiee.challengeinterview.common.ViewModelFactory
import com.bitamirshafiee.challengeinterview.common.helper.ImageLoader
import com.bitamirshafiee.challengeinterview.di.repository.scope.ApplicationScope
import com.bitamirshafiee.challengeinterview.repository.SearchUseCase
import com.bitamirshafiee.challengeinterview.questionlist.QuestionListViewModelImpl
import com.bitamirshafiee.challengeinterview.repository.SearchUseCaseImpl
import com.bitamirshafiee.challengeinterview.repository.StackOverflowApi
import dagger.Module
import dagger.Provides


@Module
class SearchModule(private val activity : FragmentActivity) {

    @Provides
    @SearchScope
    fun provideSearchUseCase(stackOverflowApi: StackOverflowApi): SearchUseCase =
        SearchUseCaseImpl(stackOverflowApi)

    @Provides
    @SearchScope
    fun provideImageLoader() : ImageLoader = ImageLoader(activity)
}