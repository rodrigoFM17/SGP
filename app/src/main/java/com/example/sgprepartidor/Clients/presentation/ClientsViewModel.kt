package com.example.sgprepartidor.Clients.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgprepartidor.Clients.data.repository.ClientsRepository
import com.example.sgprepartidor.core.data.local.Client.entities.ClientEntity
import com.example.sgprepartidor.core.storage.StorageManager

class ClientsViewModel(
    context: Context,
    val navigateToParticularClient: () -> Unit,
    val navigateToAddClient: () -> Unit,
    val clientStorage: StorageManager<ClientEntity>
) : ViewModel() {

    private val clientsRepository = ClientsRepository(context)

    private val _clients = MutableLiveData<List<ClientEntity>>()

    val clients: LiveData<List<ClientEntity>> = _clients

    suspend fun getAllClients() {
        _clients.value = clientsRepository.getAllClients()
    }

    private val _name = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()

    val name: LiveData<String> = _name
    val phone : LiveData<String> = _phone

    fun onNameChange(value: String) {
        _name.value = value
    }

    fun onPhoneChange(value: String){
        _phone.value = value
    }

    suspend fun insertClient(client: ClientEntity) {
        clientsRepository.insertClient(client)
    }




}