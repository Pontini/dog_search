package pontinisystems.dog.infra.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import pontinisystems.dog.infra.database.entities.DogEntity


@Dao
interface DogDao {
    @Transaction
    @Query("SELECT * FROM `dog`")
    suspend fun selectAll():List<DogEntity>?

    @Transaction
    @Query("SELECT * FROM `dog`  WHERE breedName LIKE '%' || :search || '%'")
    suspend fun search(search:String):List<DogEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DogEntity):Long

    @Delete
    suspend fun delete(dogEntity: DogEntity)

    @Transaction
    @Query("SELECT * FROM `dog` WHERE id=:symbol LIMIT 1")
    suspend fun selectById(symbol: String): DogEntity?

}