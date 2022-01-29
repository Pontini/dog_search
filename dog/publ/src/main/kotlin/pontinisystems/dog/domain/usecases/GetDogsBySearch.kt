package pontinisystems.dog.domain.usecases

import pontinisystems.core.Either
import pontinisystems.dog.domain.entities.Dog

interface GetDogsBySearch{
    suspend operator fun invoke(text:String?, page: Int?,limit: Int?): Either<List<Dog>, Exception>
}