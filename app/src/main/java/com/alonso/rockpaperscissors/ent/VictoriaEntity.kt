package com.alonso.rockpaperscissors.ent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "victoria")
class VictoriaEntity {
    @PrimaryKey
    var username :String = ""
    var partidasGanadas :Int = 0
    var luchasGanadas :Int = 0
}
