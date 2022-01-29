package pontinisystems.dog.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pontinisystems.dog.infra.repositories.DogRepositoryImpl
import pontinisystems.core.DefaultDispatcherProvider
import pontinisystems.core.DispactcherProvider
import pontinisystems.dog.domain.repositories.DogRepository
import pontinisystems.dog.domain.usecases.*
import pontinisystems.dog.external.datasources.DogDatasourceImpl
import pontinisystems.dog.external.remote.DogApi
import pontinisystems.dog.infra.database.Database
import pontinisystems.dog.infra.datasources.DogDatasource
import pontinisystems.dog.infra.mapper.DogEntitiesToDogMapper
import pontinisystems.dog.infra.mapper.DogRemoteModelToDogEntitiesMapper

import pontinisystems.dog.presentation.mapper.DogsToDogsUIMapper
import pontinisystems.dog.presentation.viewmodel.DogsViewModel
import retrofit2.Retrofit

val dogModuleDependency = module {

    fun provideDogApi(retrofit: Retrofit) = retrofit.create(DogApi::class.java)
    factory { provideDogApi(retrofit = get()) }

    single<DogDatasource> { DogDatasourceImpl(get())  }
    single<DogRepository> {DogRepositoryImpl(get(), get(), get(), get())  }

    single<GetDogsBySearch> { GetDogsBySearchImpl(get())  }

    single<DispactcherProvider> { DefaultDispatcherProvider() }

    factory { DogsToDogsUIMapper() }
    factory { DogRemoteModelToDogEntitiesMapper() }
    factory { DogEntitiesToDogMapper() }

    single { Database(androidContext()) }

    single { get<Database>().dogDao() }

    viewModel{
        DogsViewModel(
            get(),get(), get()
        )
    }

}

