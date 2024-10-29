package com.alonso.rockpaperscissors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.rockpaperscissors.ui.theme.RockPaperScissorsTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RockPaperScissorsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RPS(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun RPS(modifier: Modifier){
    var iconoMaquina by rememberSaveable {mutableStateOf(R.drawable.bot)}
    var maquina by rememberSaveable { mutableStateOf(0) }
    var jugador by rememberSaveable { mutableStateOf(0) }
    var tiradaMaquina = 0
    when (tiradaMaquina){
        0 -> R.drawable.bot
        1 -> iconoMaquina = R.drawable.rocks
        2 -> iconoMaquina = R.drawable.paper
        3 -> iconoMaquina = R.drawable.scissor
    }
    Column (modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Rock Paper Scissors!",
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
        )
        Image(
            painter = painterResource(iconoMaquina),
            contentDescription = "tirada máquina",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
                .size(128.dp)
        )
        Text(
            text = "Máquina: $maquina",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        HorizontalDivider(thickness = 2.dp, color = Color.LightGray, modifier = modifier.fillMaxWidth(0.8f).padding(10.dp))
        Text(
            text = "Jugador: $jugador",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        Row(modifier = modifier, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.rocks),
                contentDescription = "Piedra",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .padding(5.dp)
                    .size(100.dp)
                    .clickable {
                        tiradaMaquina = tiradaMaquina()
                    }
            )
            Image(
                painter = painterResource(R.drawable.paper),
                contentDescription = "Papel",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .padding(5.dp)
                    .size(100.dp)
            )
            Image(
                painter = painterResource(R.drawable.scissor),
                contentDescription = "Tijeras",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .size(100.dp)
                    .padding(5.dp)

            )
        }
    }
}

fun tiradaMaquina(): Int {
    return Random.nextInt(3)
}