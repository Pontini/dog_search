package pontinisystems.dog.infra.model
import com.squareup.moshi.Json

data class HeightRemoteModel(

	@Json(name="metric")
	val metric: String? = null,

	@Json(name="imperial")
	val imperial: String? = null
)