package com.gsanchez.kmpsalesmobil.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gsanchez.kmpsalesmobil.GetDeviceInformation
import com.gsanchez.kmpsalesmobil.bottombar.BottomBarScreen

class LoginScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var showMensaje: Boolean by remember { mutableStateOf(false) }
        var username: String by remember { mutableStateOf("") }
        var password: String by remember { mutableStateOf("") }
        val device = GetDeviceInformation().getDeviceInfo()

        Column (
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(36.dp))
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
                Text("debe ingresar su usuario y contraseña", fontSize = 11.sp, color = Color.Red)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button( onClick = {
                if (username.isNotEmpty() && password.isNotEmpty())
                    navigator.push(BottomBarScreen())
                else showMensaje = true
            }
            ) {
                Text("Ingresar")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text("Dispositivo: $device")
        }
    }

}