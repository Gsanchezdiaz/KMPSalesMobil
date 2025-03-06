package com.gsanchez.kmpsalesmobil

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpsalesmobil.composeapp.generated.resources.Res
import kmpsalesmobil.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showWelcome: Boolean by remember { mutableStateOf(false) }
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
            Spacer(modifier = Modifier.height(16.dp))

            Button( onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty())
                        showWelcome = true
                    }
            ) {
                    Text("Ingresar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(showWelcome) {
                Text("Bienvenido, $username", fontSize = 30.sp)
            }
        }
    }
}

