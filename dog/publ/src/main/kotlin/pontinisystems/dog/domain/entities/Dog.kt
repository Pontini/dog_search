package pontinisystems.dog.domain.entities

import java.io.Serializable

data class Dog(
    val temperament: String? = null,
    val breedName: String,
    val breedFor: String,
    val url: String?,
    val id: String,
    val lifeSpan: String?,
) : Serializable