package com.alonso.rockpaperscissors.dal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alonso.rockpaperscissors.ent.VictoriaEntity

@Dao
interface VictoriaDAO {
    @Query("SELECT * FROM victoria")
    suspend fun getAll(): List<VictoriaEntity>
    @Query("SELECT * FROM victoria WHERE username = :username")
    suspend fun get(username: String): VictoriaEntity
    @Insert
    suspend fun insert(victoria: VictoriaEntity)
    @Update
    suspend fun update(victoria: VictoriaEntity)
    @Delete
    suspend fun delete(victoria: VictoriaEntity)
}