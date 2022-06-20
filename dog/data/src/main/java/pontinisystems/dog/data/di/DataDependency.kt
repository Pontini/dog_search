package pontinisystems.dog.data.di

import org.koin.dsl.module
import pontinisystems.dog.data.datasources.DogDatasourceImpl
import pontinisystems.dog.data.infra.database.Database
import pontinisystems.dog.data.infra.datasources.DogDatasource
import pontinisystems.dog.data.infra.mapper.DogEntitiesToDogMapper
import pontinisystems.dog.data.infra.mapper.DogRemoteModelToDogEntitiesMapper
import pontinisystems.dog.data.infra.repositories.DogRepositoryImpl
import pontinisystems.dog.data.remote.DogApi
import pontinisystems.dog.domain.repositories.DogRepository
import retrofit2.Retrofit


val dataModule = module {

    fun provideDogApi(retrofit: Retrofit) = retrofit.create(DogApi::class.java)
    factory { provideDogApi(retrofit = get()) }

    factory { DogEntitiesToDogMapper() }

    single { Database(context = get()) }

    single { get<Database>().dogDao() }

    single<DogDatasource> { DogDatasourceImpl(get()) }
    single<DogRepository> { DogRepositoryImpl(get(), get(), get(), get()) }
    factory { DogRemoteModelToDogEntitiesMapper() }

}
