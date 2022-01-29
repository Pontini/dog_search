package pontinisystems.dog.domain.repositories

import pontinisystems.core.Either
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.dog.domain.errors.Failure

interface DogRepository {
    suspend fun search(text: String?, page: String, limit: String):Either<List<Dog>, Failure>
}