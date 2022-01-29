package pontinisystems.dog.infra.mapper

import pontinisystems.core.CustomMapper
import pontinisystems.dog.domain.entities.Dog
import pontinisystems.dog.infra.database.entities.DogEntity

class DogEntitiesToDogMapper : CustomMapper<List<DogEntity>, List<Dog>> {
    override fun mapFrom(from: List<DogEntity>): List<Dog> {
        val list = arrayListOf<Dog>()
        try {
            from.forEach { item ->
                list.add(
                    Dog(
                        id = item.id,
                        url = item.url,
                        breedName = item.breedName,
                        breedFor = item.breedFor,
                        temperament = item.temperament,
                        lifeSpan = item.lifeSpan
                    )
                )
            }
        } catch (e: Throwable) {
            print(e)
        }
        return list
    }
}