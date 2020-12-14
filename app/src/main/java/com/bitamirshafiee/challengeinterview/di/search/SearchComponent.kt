package com.bitamirshafiee.challengeinterview.di.search

import com.bitamirshafiee.challengeinterview.questionlist.SearchFragment
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = [SearchModule::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)
}