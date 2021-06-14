package com.bitamirshafiee.challengeinterview.di

import com.bitamirshafiee.challengeinterview.QuestionListViewModelTest
import com.bitamirshafiee.challengeinterview.di.search.SearchComponent
import com.bitamirshafiee.challengeinterview.di.search.SearchModule
import com.bitamirshafiee.challengeinterview.di.search.SearchScope
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponentTest  : SearchComponent{

    fun inject(viewModel : QuestionListViewModelTest)
}