package pontinisystems.core.di


import org.koin.dsl.module
import pontinisystems.core.DefaultDispatcherProvider
import pontinisystems.core.DispactcherProvider

val coreModuleDependency = module {
    single<DispactcherProvider> { DefaultDispatcherProvider() }
}