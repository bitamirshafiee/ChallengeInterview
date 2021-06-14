package com.bitamirshafiee.challengeinterview.di

import com.bitamirshafiee.challengeinterview.di.repository.component.RepositoryComponent
import com.bitamirshafiee.challengeinterview.di.repository.module.ApplicationModule
import com.bitamirshafiee.challengeinterview.di.repository.module.NetworkingModule
import com.bitamirshafiee.challengeinterview.di.repository.scope.NetworkScope
import dagger.Component

@NetworkScope
@Component(modules = [NetworkingModule::class, ApplicationModule::class])
interface TestRepositoryComponent : RepositoryComponent