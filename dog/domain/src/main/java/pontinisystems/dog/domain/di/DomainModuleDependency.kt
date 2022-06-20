package pontinisystems.dog.domain.di

import org.koin.dsl.module
import pontinisystems.dog.domain.usecases.*

val domainModuleDependency = module {
    single<GetDogsBySearch> { GetDogsBySearchImpl(get()) }
}

