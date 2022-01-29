package pontinisystems.dog.infra.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dog")
data class DogEntity(
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("url")
    val url: String?,

    @SerializedName("breed_name")
    val breedName: String,

    @SerializedName("breed_for")
    val breedFor: String,

    @SerializedName("life_span")
    val lifeSpan: String,

    @SerializedName("temperament")
    val temperament: String,
)