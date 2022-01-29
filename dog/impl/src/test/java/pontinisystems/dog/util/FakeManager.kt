package pontinisystems.dog.util

import pontinisystems.dog.domain.entities.Dog

class Factory {
    companion object {
        fun mockDogs() =
            listOf(
                Dog(
                    temperament = "skillful",
                    breedName = "Retriever ",
                    breedFor = "imperial",
                    url = "my_url",
                    id = "45654456",
                    lifeSpan = "12 Years",
                )
            )
    }
}