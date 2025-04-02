package com.example.sgprepartidor.Clients.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import com.example.sgprepartidor.components.ButtonComponent
import com.example.sgprepartidor.components.TextFieldComponent
import com.example.sgprepartidor.core.data.local.Client.entities.ClientEntity
import com.example.sgprepartidor.layouts.Container
import kotlinx.coroutines.launch

@Composable
fun AddClient(clientsViewModel: ClientsViewModel) {

    val name by clientsViewModel.name.observeAsState("")
    val phone by clientsViewModel.phone.observeAsState("")

    Container(
        headerTitle = "Agregar Cliente"
    ) {

        TextFieldComponent(
            value = name,
            onValueChange = clientsViewModel::onNameChange,
            placeholder = "Nombre"
        )

        TextFieldComponent(
            value = phone,
            onValueChange = clientsViewModel::onPhoneChange,
            placeholder = "Numero de telefono"
        )

        ButtonComponent(
            text = "Guardar",
            onClick = {
                clientsViewModel.viewModelScope.launch {
                    clientsViewModel.insertClient(ClientEntity(
                        name = name,
                        phone = phone
                    ))
                }
            }
        )
    }
}