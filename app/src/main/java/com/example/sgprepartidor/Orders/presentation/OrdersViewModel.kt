package com.example.sgprepartidor.Orders.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgprepartidor.Orders.data.repository.OrderRepository
import com.example.sgprepartidor.core.data.local.Order.entities.OrderEntity

class OrdersViewModel(context: Context) : ViewModel() {

    private val orderRepository = OrderRepository(context)
    private val _orders = MutableLiveData<List<OrderEntity>>()

    val orders : LiveData<List<OrderEntity>> = _orders

    suspend fun getAllPendingOrders() {
        _orders.value = orderRepository.getAllPending()
    }

    private val _clientId = MutableLiveData<Int>()

    val clientId : LiveData<Int> = _clientId

    suspend fun insertOrder(order: OrderEntity) {
        orderRepository.insertOrder(order)
    }


}