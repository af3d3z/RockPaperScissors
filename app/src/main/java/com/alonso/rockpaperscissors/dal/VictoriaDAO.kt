package com.alonso.rockpaperscissors.dal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alonso.rockpaperscissors.ent.VictoriaEntity

@Dao
interface VictoriaDAO {
    @Query("SELECT * FROM victoria ORDER BY partidasGanadas desc LIMIT 10")
    suspend fun getAll(): List<VictoriaEntity>
    @Query("SELECT * FROM victoria WHERE username = :username")
    suspend fun get(username: String): VictoriaEntity
    @Query("SELECT EXISTS(SELECT 1 FROM victoria WHERE username = :username)")
    suspend fun exists(username: String): Boolean
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(victoria: VictoriaEntity)
    @Update
    suspend fun update(victoria: VictoriaEntity)
}