package pontinisystems.dog.infra.model

import com.squareup.moshi.Json
import pontinisystems.dog.infra.model.BreedRemoteModel

data class DogRemoteModel(

	@Json(name="id")
	val id: String? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="breeds")
	val breeds: List<BreedRemoteModel?>? = null
)