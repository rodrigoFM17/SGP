package com.example.sgprepartidor.Clients.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.sgprepartidor.Clients.presentation.components.ClientCard
import com.example.sgprepartidor.layouts.Container

@Composable
fun ClientScreen(clientViewModel: ClientViewModel) {

    val clients by clientViewModel.clients.observeAsState(emptyList())

    Container(
        headerTitle = "Clientes"
    ) {

        LazyColumn {
            items(clients){ client ->
                ClientCard(client)
            }
        }
    }
}