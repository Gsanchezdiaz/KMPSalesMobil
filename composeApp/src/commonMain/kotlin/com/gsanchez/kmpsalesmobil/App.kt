package com.gsanchez.kmpsalesmobil

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.gsanchez.kmpsalesmobil.login.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator( screen = LoginScreen()) { navigator ->
            FadeTransition(navigator)
        }
    }
}
