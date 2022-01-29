package pontinisystems.searchdog

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pontinisystems.dog.di.dogModuleDependency
import pontinisystems.network.di.retrofitModule

class TmdbControlApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@TmdbControlApp)
            modules(provideModules())
        }
    }

    private fun provideModules() =
        listOf(
            retrofitModule,
            dogModuleDependency
        )
}