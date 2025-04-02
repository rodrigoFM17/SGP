package com.example.sgprepartidor.Orders.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.example.sgprepartidor.Orders.presentation.components.OrderCard
import com.example.sgprepartidor.layouts.Container
import kotlinx.coroutines.launch

@Composable
fun OrdersScreen(ordersViewModel: OrdersViewModel) {

    val orders by ordersViewModel.orders.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        ordersViewModel.viewModelScope.launch {
            ordersViewModel.getAllPendingOrders()
        }
    }

    Container (
        headerTitle = "Pedidos"
    ){
        if(orders.isEmpty()){
            Text( text = "No tienes pedidos agendados")
        }

        LazyColumn {
            items(orders) { order ->
                OrderCard(order)
            }
        }

    }
}