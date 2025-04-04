package com.gsanchez.kmpsalesmobil.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.gsanchez.kmpsalesmobil.network.NetworkUtils.httpClient
import com.gsanchez.kmpsalesmobil.network.model.ApiResponse
import com.gsanchez.kmpsalesmobil.network.model.Hero
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object HomeTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        var superHeroName by remember { mutableStateOf("") }
        var superHeroList by remember { mutableStateOf<List<Hero>>(emptyList()) }
        Box(Modifier.fillMaxSize().background(Color.White)) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Super Hero List", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = superHeroName,
                        onValueChange = { superHeroName = it })
                    Spacer(modifier = Modifier.width(6.dp))
                    Button(
                        modifier = Modifier.wrapContentWidth(),
                        onClick = { getSuperHeroList(superHeroName) { superHeroList = it } }) {
                        Text("Buscar")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (superHeroList.isEmpty())
                    Text("No se encontraron resultados")
                else
                    LazyColumn {
                        items(superHeroList) { hero ->
                            Text(hero.name)
                        }
                    }
            }
        }

    }

    private fun getSuperHeroList(
        superHeroName: String,
        onSuccessResponse: (List<Hero>) -> Unit
    ) {
        if (superHeroName.isBlank()) return
        val url =
            "https://superheroapi.com/api/b2b7715cbbe424cb15d1bcd164df02cd/search/$superHeroName"

        CoroutineScope(Dispatchers.Default).launch {
            try {
                val data = httpClient.get(url).body<ApiResponse>()
                if (data.ok == "error") return@launch
                onSuccessResponse(httpClient.get(url).body<ApiResponse>().results)
            } catch (e: Exception) {
                println(e.message ?: "Error")
            }
        }
    }
}