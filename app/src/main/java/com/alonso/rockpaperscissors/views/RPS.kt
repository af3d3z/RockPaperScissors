package com.alonso.rockpaperscissors

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alonso.rockpaperscissors.ent.VictoriaEntity
import com.alonso.rockpaperscissors.ui.theme.RockPaperScissorsTheme
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt




@Composable
fun RPS(modifier: Modifier, username: String, navController: NavController){
    var iconoMaquina by rememberSaveable {mutableStateOf(R.drawable.bot)}
    var maquina by rememberSaveable { mutableStateOf(0) }
    var jugador by rememberSaveable { mutableStateOf(0) }
    var popUp by rememberSaveable{mutableStateOf(false)}
    var ganador by remember {mutableStateOf(0)}
    var tiradaMaquina by rememberSaveable{mutableStateOf(0)}
    var ctx = LocalContext.current

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
            text = "Computer: $maquina",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        HorizontalDivider(thickness = 2.dp, color = Color.LightGray, modifier = modifier.fillMaxWidth(0.8f).padding(10.dp))
        Text(
            text = "$username: $jugador",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
        Row(modifier = modifier, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.rocks),
                contentDescription = "Rock",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .padding(5.dp)
                    .size(100.dp)
                    .clickable {
                        tiradaMaquina = tiradaMaquina()
                        when(tiradaMaquina) {
                            1-> iconoMaquina = R.drawable.rocks
                            2-> iconoMaquina = R.drawable.paper
                            3-> iconoMaquina = R.drawable.scissor
                        }
                        var puntuacion = checkWinner(playerMove = 1, machineMove = tiradaMaquina, ctx = ctx)
                        when(puntuacion){
                            1 -> {
                                jugador++
                                ganador = 1
                                MainActivity.coroutine.launch {
                                    var user = MainActivity.db.victoriaDao().get(username = username)
                                    MainActivity.db.victoriaDao().update(VictoriaEntity(username=user.username, partidasGanadas = user.partidasGanadas, luchasGanadas = user.luchasGanadas + 1))
                                }
                            }
                            2 -> {
                                maquina++
                                ganador = 2
                            }
                        }

                        if(jugador >= 5){
                            jugador = 0
                            maquina = 0
                            popUp = true
                            MainActivity.coroutine.launch {
                                var user = MainActivity.db.victoriaDao().get(username = username)
                                MainActivity.db.victoriaDao().update(VictoriaEntity(username=user.username, partidasGanadas = user.partidasGanadas +1, luchasGanadas = user.luchasGanadas))
                            }
                        }else if(maquina >= 5) {
                            jugador = 0
                            maquina = 0
                            popUp = true
                        }
                    }
            )
            Image(
                painter = painterResource(R.drawable.paper),
                contentDescription = "Paper",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .padding(5.dp)
                    .size(100.dp)
                    .clickable {
                        tiradaMaquina = tiradaMaquina()
                        when(tiradaMaquina) {
                            1-> iconoMaquina = R.drawable.rocks
                            2-> iconoMaquina = R.drawable.paper
                            3-> iconoMaquina = R.drawable.scissor
                        }
                        var puntuacion = checkWinner(playerMove = 1, machineMove = tiradaMaquina, ctx = ctx)
                        when(puntuacion){
                            1 -> {
                                jugador++
                                ganador = 1
                                MainActivity.coroutine.launch {
                                    var user = MainActivity.db.victoriaDao().get(username = username)
                                    MainActivity.db.victoriaDao().update(VictoriaEntity(username=user.username, partidasGanadas = user.partidasGanadas, luchasGanadas = user.luchasGanadas + 1))
                                }
                            }
                            2 -> {
                                maquina++
                                ganador = 2
                            }
                        }

                        if(jugador >= 5){
                            jugador = 0
                            maquina = 0
                            popUp = true
                            MainActivity.coroutine.launch {
                                var user = MainActivity.db.victoriaDao().get(username = username)
                                MainActivity.db.victoriaDao().update(VictoriaEntity(username=user.username, partidasGanadas = user.partidasGanadas +1, luchasGanadas = user.luchasGanadas))
                            }
                        }else if(maquina >= 5) {
                            jugador = 0
                            maquina = 0
                            popUp = true
                        }
                    }
            )
            Image(
                painter = painterResource(R.drawable.scissor),
                contentDescription = "Scissors",
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .size(100.dp)
                    .padding(5.dp)
                    .clickable {
                        tiradaMaquina = tiradaMaquina()
                        when(tiradaMaquina) {
                            1-> iconoMaquina = R.drawable.rocks
                            2-> iconoMaquina = R.drawable.paper
                            3-> iconoMaquina = R.drawable.scissor
                        }
                        var puntuacion = checkWinner(playerMove = 1, machineMove = tiradaMaquina, ctx = ctx)
                        when(puntuacion){
                            1 -> {
                                jugador++
                                ganador = 1
                                MainActivity.coroutine.launch {
                                    var user = MainActivity.db.victoriaDao().get(username = username)
                                    MainActivity.db.victoriaDao().update(VictoriaEntity(username=user.username, partidasGanadas = user.partidasGanadas, luchasGanadas = user.luchasGanadas + 1))
                                }
                            }
                            2 -> {
                                maquina++
                                ganador = 2
                            }
                        }

                        if(jugador >= 5){
                            jugador = 0
                            maquina = 0
                            popUp = true
                            MainActivity.coroutine.launch {
                                var user = MainActivity.db.victoriaDao().get(username = username)
                                MainActivity.db.victoriaDao().update(VictoriaEntity(username=user.username, partidasGanadas = user.partidasGanadas +1, luchasGanadas = user.luchasGanadas))
                            }
                        }else if(maquina >= 5) {
                            jugador = 0
                            maquina = 0
                            popUp = true
                        }
                    }
            )
        }
    }
    Row(verticalAlignment = Alignment.Bottom, modifier = modifier.fillMaxSize()) {
        Text(
            text = "Ver liga",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("score")
                }
        )
    }
    AnimatedVisibility(visible = popUp) {
        Column (modifier = Modifier.fillMaxSize().clickable { popUp = false }, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .background(Color.White)
                    .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(25.dp)) ) {
                Text(
                    text = if (ganador == 1) "Gana el jugador." else "Gana la máquina.",
                    fontSize = 40.sp,
                    lineHeight = 40.sp,
                    modifier = Modifier.wrapContentSize(),
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}

fun tiradaMaquina(): Int {
    val tirada = Random.nextInt(1..3)
    Log.d(":::RPS", "Máquina: $tirada")
    return tirada
}

/**
 * Recibe la jugada del jugador y la de la máquina, determina quien gana y si hay empate o no.
 * @param playerMove movimiento del jugador
 * @param machineMove movimiento del ordenador
 * @return Si devuelve 0 es empate, 1 gana el jugador, 2 gana la máquina
 */
fun checkWinner(playerMove: Int, machineMove: Int, ctx: Context): Int {
    var state = 0
    Log.d(":::RPS", "Player Move: $playerMove Machine Move: $machineMove")
    if(playerMove == 1 && machineMove == 3 || playerMove == 2 && machineMove == 1 || playerMove == 3 && machineMove == 2) {
        state = 1
        Toast.makeText(ctx, "Gana el jugador", Toast.LENGTH_LONG).show()
    }else if(playerMove == 1 && machineMove == 2 || playerMove == 2 && machineMove == 3 || playerMove == 3 && machineMove == 1){
        state = 2
        Toast.makeText(ctx, "Gana la máquina", Toast.LENGTH_LONG).show()
    }
    else{
        Toast.makeText(ctx, "Empate", Toast.LENGTH_LONG).show()
    }

    return state
}

