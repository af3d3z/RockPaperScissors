package com.alonso.rockpaperscissors.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.rockpaperscissors.MainActivity
import com.alonso.rockpaperscissors.ent.VictoriaEntity

@Composable
fun ScoreBoard(){
    var listaJugadores = remember { mutableStateListOf<VictoriaEntity>() }
    LaunchedEffect(Unit) {
        listaJugadores.addAll(MainActivity.db.victoriaDao().getAll())
        Log.d(":::rps", listaJugadores.toString())
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Score:",
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Row {
            Text(text = "Username", fontSize = 16.sp, modifier = Modifier.padding(5.dp).weight(1f), textAlign = TextAlign.Center)
            Text(text = "Luchas ganadas", fontSize = 16.sp, modifier = Modifier.padding(5.dp).weight(1f), textAlign = TextAlign.Center)
            Text(text = "Partidas ganadas", fontSize = 16.sp, modifier = Modifier.padding(5.dp).weight(1f), textAlign = TextAlign.Center)
        }
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            items(items = listaJugadores){ jugador ->
                Row {
                    Text(text = jugador.username, fontSize = 16.sp, modifier = Modifier.padding(5.dp).weight(1f), textAlign = TextAlign.Center)
                    Text(text = jugador.luchasGanadas.toString(), fontSize = 16.sp, modifier = Modifier.padding(5.dp).weight(1f), textAlign = TextAlign.Center)
                    Text(text = jugador.partidasGanadas.toString(), fontSize = 16.sp, modifier = Modifier.padding(5.dp).weight(1f), textAlign = TextAlign.Center)
                }
            }
        }
    }
}