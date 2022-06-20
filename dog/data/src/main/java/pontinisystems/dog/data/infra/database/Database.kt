package pontinisystems.dog.data.infra.database

import android.content.Context
import androidx.room.Room
import pontinisystems.dog.data.infra.database.dao.DogDao

class Database(context: Context) {

    companion object {
        const val DB_NAME = "database-dog"
    }

    private val db = Room.databaseBuilder(context, DataBaseBuilder::class.java, DB_NAME)
        .fallbackToDestructiveMigration().build()


    fun dogDao(): DogDao {
        return db.dogEntityDao()
    }

}