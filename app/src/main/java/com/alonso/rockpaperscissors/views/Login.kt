package com.alonso.rockpaperscissors.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alonso.rockpaperscissors.MainActivity
import com.alonso.rockpaperscissors.ent.VictoriaEntity
import kotlinx.coroutines.launch

@Composable
fun Login(navController: NavController) {
    var username by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Login",
            fontSize = 35.sp,
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.padding(0.dp, 20.dp)
        )
        Button(onClick = {
            MainActivity.coroutine.launch {
                var exists : Boolean = MainActivity.db.victoriaDao().exists(username = username)
                if (!exists) {
                    MainActivity.db.victoriaDao().insert(victoria = VictoriaEntity(username = username))
                    Log.d(":::rps", "New user: $username")
                }
            }
            navController.navigate("rps/${username}")
        }, ) {
            Text("Enter")
        }
    }
}