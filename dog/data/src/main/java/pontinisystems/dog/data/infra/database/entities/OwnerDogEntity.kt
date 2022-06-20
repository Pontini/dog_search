package pontinisystems.dog.data.infra.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "owner_dog")
data class OwnerDogEntity(
    @SerializedName("id")
    @PrimaryKey
    val id: String,

    @SerializedName("photo")
    val photo: String?,

    @SerializedName("name")
    val name: String,
)