package com.bitamirshafiee.challengeinterview.di.repository.component

import com.bitamirshafiee.challengeinterview.di.repository.module.ApplicationModule
import com.bitamirshafiee.challengeinterview.di.repository.module.NetworkingModule
import com.bitamirshafiee.challengeinterview.di.repository.scope.NetworkScope
import com.bitamirshafiee.challengeinterview.di.search.SearchComponent
import com.bitamirshafiee.challengeinterview.di.search.SearchModule
import dagger.Component

@NetworkScope
@Component(modules = [NetworkingModule::class, ApplicationModule::class])
interface RepositoryComponent {

    fun provideSearchComponent(searchModule: SearchModule) : SearchComponent
}