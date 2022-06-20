package pontinisystems.searchdog

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pontinisystems.core.di.coreModuleDependency
import pontinisystems.dog.data.di.dataModule
import pontinisystems.dog.domain.di.domainModuleDependency
import pontinisystems.dog.presentation.di.presentationModuleDependency
import pontinisystems.network.di.networkModule

class DogSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@DogSearchApp)
            modules(provideModules())
        }
    }

    private fun provideModules() =
        listOf(
            networkModule,
            dataModule,
            domainModuleDependency,
            coreModuleDependency,
            presentationModuleDependency
        )
}