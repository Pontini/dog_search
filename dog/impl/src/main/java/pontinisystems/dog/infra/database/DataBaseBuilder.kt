package pontinisystems.dog.infra.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pontinisystems.dog.infra.database.DataBaseBuilder.Companion.DB_VERSION
import pontinisystems.dog.infra.database.dao.DogDao
import pontinisystems.dog.infra.database.entities.DogEntity


@Database(
    entities = [DogEntity::class], version = DB_VERSION
)

abstract class DataBaseBuilder : RoomDatabase() {
    companion object {
        const val DB_VERSION = 100
    }

    abstract fun dogEntityDao(): DogDao
}