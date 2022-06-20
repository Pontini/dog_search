package pontinisystems.dog.data.infra.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pontinisystems.dog.data.infra.database.DataBaseBuilder.Companion.DB_VERSION
import pontinisystems.dog.data.infra.database.dao.DogDao
import pontinisystems.dog.data.infra.database.entities.DogEntity
import pontinisystems.dog.data.infra.database.entities.OwnerDogEntity


@Database(
    entities = [DogEntity::class, OwnerDogEntity::class], version = DB_VERSION
)

abstract class DataBaseBuilder : RoomDatabase() {
    companion object {
        const val DB_VERSION = 100
    }

    abstract fun dogEntityDao(): DogDao
}