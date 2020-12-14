package com.bitamirshafiee.challengeinterview.di.search

import com.bitamirshafiee.challengeinterview.di.networking.NetworkingComponent
import com.bitamirshafiee.challengeinterview.questionlist.SearchFragment
import dagger.Component

@SearchScope
@Component(modules = [SearchModule::class], dependencies = [NetworkingComponent::class])
interface SearchComponent {

    fun inject(searchFragment: SearchFragment)
}