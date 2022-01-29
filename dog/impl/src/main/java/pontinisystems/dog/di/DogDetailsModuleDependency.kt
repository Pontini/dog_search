package pontinisystems.dog.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pontinisystems.dog.infra.repositories.DogRepositoryImpl
import pontinisystems.core.DefaultDispatcherProvider
import pontinisystems.core.DispactcherProvider
import pontinisystems.dog.domain.repositories.DogRepository
import pontinisystems.dog.external.datasources.DogDatasourceImpl
import pontinisystems.dog.external.remote.DogApi
import pontinisystems.dog.infra.database.Database
import pontinisystems.dog.infra.datasources.DogDatasource
import pontinisystems.dog.infra.mapper.DogEntitiesToDogMapper
import pontinisystems.dog.infra.mapper.DogRemoteModelToDogEntitiesMapper
import pontinisystems.dog.presentation.viewmodel.DogDetailsViewModel
import retrofit2.Retrofit

val DogDetailsModuleDependency = module {

    fun provideDogApi(retrofit: Retrofit) = retrofit.create(DogApi::class.java)
    factory { provideDogApi(retrofit = get()) }

    single<DogDatasource> { DogDatasourceImpl(get()) }


    single<DispactcherProvider> { DefaultDispatcherProvider() }

    single { Database(androidContext()) }
    single { get<Database>().dogDao() }

    single<DogRepository> { DogRepositoryImpl(get(), get(), get(), get()) }

    viewModel {
        DogDetailsViewModel()
    }

    factory { DogRemoteModelToDogEntitiesMapper() }
    factory { DogEntitiesToDogMapper() }

}

