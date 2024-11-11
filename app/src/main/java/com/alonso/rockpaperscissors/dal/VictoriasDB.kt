package com.alonso.rockpaperscissors.dal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alonso.rockpaperscissors.ent.VictoriaEntity

@Database(entities = [VictoriaEntity::class], version = 1, exportSchema = true)
abstract class VictoriaDB: RoomDatabase()   {
    abstract fun victoriaDao(): VictoriaDAO
}