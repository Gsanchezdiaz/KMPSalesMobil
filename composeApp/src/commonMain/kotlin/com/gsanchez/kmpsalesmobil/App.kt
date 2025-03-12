package com.gsanchez.kmpsalesmobil

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.ScaleTransition
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpsalesmobil.composeapp.generated.resources.Res
import kmpsalesmobil.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator( screen = LoginScreen()) { navigator ->
            FadeTransition(navigator)
        }
    }
}

class LoginScreen:Screen{
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showMensaje: Boolean by remember { mutableStateOf(false) }
        var username: String by remember { mutableStateOf("") }
        var password: String by remember { mutableStateOf("") }

        Column (
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Acceso Seguro", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Inicia sesion para ingresar a tu area de cliente", fontSize = 10.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = username,
                label = { Text("Usuario") },
                onValueChange = { username = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                label = { Text("Contraseña") },
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation()

            )
            AnimatedVisibility(showMensaje) {
                Text("debe ingresar su usuario o contraseña", fontSize = 11.sp, color = Color.Red)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button( onClick = {
                if (username.isNotEmpty() && password.isNotEmpty())
                    navigator.push(SecondScreen())
                else showMensaje = true
            }
            ) {
                Text("Ingresar")
            }
        }
    }

}

class SecondScreen:Screen{

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Column (
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Bienvenido Segunda Pantalla", fontSize = 30.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navigator.push(LoginScreen())}) { Text("Salir")}
        }
    }
}