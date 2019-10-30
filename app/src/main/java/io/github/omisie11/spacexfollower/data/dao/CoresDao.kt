package io.github.omisie11.spacexfollower.data.dao

import androidx.room.*
import io.github.omisie11.spacexfollower.data.model.Core
import kotlinx.coroutines.flow.Flow

@Dao
interface CoresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCores(cores: List<Core>)

    @Transaction
    suspend fun replaceCoresData(cores: List<Core>) {
        deleteAllCores()
        insertCores(cores)
    }

    @Query("SELECT * FROM cores_table")
    fun getAllCoresFlow(): Flow<List<Core>>

    @Query("DELETE FROM cores_table")
    suspend fun deleteAllCores()
}