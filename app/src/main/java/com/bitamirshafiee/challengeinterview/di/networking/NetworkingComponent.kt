package com.bitamirshafiee.challengeinterview.di.networking

import com.bitamirshafiee.challengeinterview.di.search.SearchComponent
import com.bitamirshafiee.challengeinterview.di.search.SearchModule
import com.bitamirshafiee.challengeinterview.repository.SearchUseCase
import dagger.Component

@NetworkScope
@Component(modules = [NetworkingModule::class])
interface NetworkingComponent {

    fun provideSearchComponent(searchModule: SearchModule) : SearchComponent
}