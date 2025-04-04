package com.example.sgprepartidor.Clients.data.repository

import android.content.Context
import com.example.sgprepartidor.core.data.local.Client.entities.ClientEntity
import com.example.sgprepartidor.core.data.local.appDatabase.DatabaseProvider

class ClientsRepository(context: Context) {

    private val clientDAO = DatabaseProvider.getDatabase(context).clientDAO()

    suspend fun insertClient(client: ClientEntity) {
        clientDAO.insertClient(client)
    }

    suspend fun getAllClients(): List<ClientEntity>{
        return clientDAO.getAllClients()
    }
}