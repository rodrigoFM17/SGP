package com.example.sgprepartidor.Orders.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgprepartidor.core.data.local.Order.entities.OrderEntity

class OrdersViewModel : ViewModel() {

    private val _orders = MutableLiveData<List<OrderEntity>>()

    val orders : LiveData<List<OrderEntity>> = _orders
}