package pontinisystems.dog.domain.usecases

import pontinisystems.core.Either
import pontinisystems.dog.domain.errors.Failure
import pontinisystems.dog.domain.repositories.DogRepository
import pontinisystems.dog.domain.entities.Dog

class GetDogsBySearchImpl(private val repository: DogRepository) : GetDogsBySearch {
    override suspend fun invoke(
        text: String?,
        page: Int?,
        limit: Int?
    ): Either<List<Dog>, Failure> {
        if (text == null || page == null || limit == null) {
            return Either.Error(Failure.InputInvalid(message = "My custom message that my domain must understand"))
        }
        return repository.search(text = text, page = page.toString(), limit = limit.toString())
    }

}