package com.example.sgprepartidor.Clients.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewModelScope
import com.example.sgprepartidor.Clients.presentation.components.ClientCard
import com.example.sgprepartidor.components.ButtonComponent
import com.example.sgprepartidor.layouts.Container
import kotlinx.coroutines.launch

@Composable
fun ClientsScreen(clientsViewModel: ClientsViewModel) {

    val clients by clientsViewModel.clients.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        clientsViewModel.viewModelScope.launch {
            clientsViewModel.getAllClients()
        }
    }

    Container(
        headerTitle = "Clientes"
    ) {

        if (clients.isEmpty()){
            Text( text = "Aun no has registrado ningun cliente")
        }

        ButtonComponent(
            onClick = clientsViewModel.navigateToAddClient,
            icon = Icons.Default.Add,
            text = "Agregar Cliente"
        )

        LazyColumn {
            items(clients){ client ->
                ClientCard(client) {
                    clientsViewModel.clientStorage.saveInStorage(client)
                    clientsViewModel.navigateToParticularClient()
                }
            }
        }
    }
}