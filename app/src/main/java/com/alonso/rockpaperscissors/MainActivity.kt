package com.alonso.rockpaperscissors

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.alonso.rockpaperscissors.dal.VictoriaDAO
import com.alonso.rockpaperscissors.dal.VictoriaDB
import com.alonso.rockpaperscissors.ui.theme.RockPaperScissorsTheme
import com.alonso.rockpaperscissors.views.Login
import com.alonso.rockpaperscissors.views.ScoreBoard
import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity() {
    companion object {
        lateinit var db: VictoriaDB
        lateinit var coroutine: CoroutineScope
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            db = Room.databaseBuilder(
                applicationContext,
                VictoriaDB::class.java,
                "rps-db"
            ).build()
            coroutine = rememberCoroutineScope()
            val navController = rememberNavController()
            NavHost(
                navController,
                startDestination = "login"
            ){
                composable("login") {
                    Login(navController)
                }
                composable("rps/{username}") { backStackEntry ->
                    RockPaperScissorsTheme {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            RPS(
                                Modifier.padding(innerPadding),
                                backStackEntry.arguments?.getString("username").toString(),
                                navController
                            )
                        }
                    }
                }
                composable("score") {
                    ScoreBoard()
                }
            }
        }
    }
}


