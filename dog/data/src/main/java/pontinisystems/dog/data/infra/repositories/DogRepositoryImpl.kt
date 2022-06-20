package pontinisystems.dog.data.infra.repositories

import pontinisystems.core.Either
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.network.CustomException
import pontinisystems.dog.domain.errors.Failure
import pontinisystems.dog.domain.repositories.DogRepository
import pontinisystems.dog.data.infra.database.dao.DogDao
import pontinisystems.dog.data.infra.database.entities.DogEntity
import pontinisystems.dog.data.infra.datasources.DogDatasource
import pontinisystems.dog.data.infra.mapper.DogEntitiesToDogMapper
import pontinisystems.dog.data.infra.mapper.DogRemoteModelToDogEntitiesMapper

class DogRepositoryImpl(
    private val dogRemoteModelToDogEntitiesMapper: DogRemoteModelToDogEntitiesMapper,
    private val dogEntitiesToDogMapper: DogEntitiesToDogMapper,
    private val datasource: DogDatasource,
    private val dogDao: DogDao
) : DogRepository {

    private suspend fun selectAllDog(search: String?): List<DogEntity>? {
       return search?.let { dogDao.search(it) } ?: run {
            dogDao.selectAll()
        }
    }

    override suspend fun search(
        text: String?,
        page: String,
        limit: String
    ): Either<List<Dog>, Failure> {
        return try {
            val data = datasource.search(text = text, page = page, limit = limit)
            val dogEntities = dogRemoteModelToDogEntitiesMapper.mapFrom(data)

            Either.Success(dogEntitiesToDogMapper.mapFrom(dogEntities))

        } catch (e: CustomException) {
            when (e) {
                is CustomException.TimeOutException -> {
                    Either.Error(
                        Failure.NetworkFailure(message = "My custom message that my domain must understand")
                    )
                }
                is CustomException.Network -> {
                    val cache = selectAllDog(text)
                    if (cache.isNullOrEmpty()) {
                        Either.Success(listOf())
                    } else {
                        Either.Success(dogEntitiesToDogMapper.mapFrom(cache))
                    }
                }

                is CustomException.ResponseBodyError -> Either.Error(
                    Failure.NetworkFailure(message = "My custom message that my domain must understand")
                )
                is CustomException.SessionExpired -> Either.Error(
                    Failure.NetworkFailure(message = "My custom message that my domain must understand")
                )
                is CustomException.Unknown -> {
                    Either.Error(Failure.Unknown("My custom message that my domain must understand"))
                }
            }

        }
    }
}

