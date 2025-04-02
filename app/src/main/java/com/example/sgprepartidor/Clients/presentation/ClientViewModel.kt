package com.example.sgprepartidor.Clients.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgprepartidor.core.data.local.Client.entities.ClientEntity

class ClientViewModel : ViewModel() {

    private val _clients = MutableLiveData<List<ClientEntity>>()

    val clients: LiveData<List<ClientEntity>> = _clients


}