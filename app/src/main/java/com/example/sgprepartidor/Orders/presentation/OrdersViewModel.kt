package com.example.sgprepartidor.Orders.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgprepartidor.Clients.data.repository.ClientsRepository
import com.example.sgprepartidor.Orders.data.repository.OrderRepository
import com.example.sgprepartidor.Products.data.repository.ProductRepository
import com.example.sgprepartidor.core.data.local.Client.entities.ClientEntity
import com.example.sgprepartidor.core.data.local.Order.entities.OrderEntity
import com.example.sgprepartidor.core.data.local.Product.entities.ProductEntity

class OrdersViewModel(context: Context) : ViewModel() {

    private val orderRepository = OrderRepository(context)
    private val productsRepository = ProductRepository(context)
    private val clientRepository = ClientsRepository(context)

    private val _orders = MutableLiveData<List<OrderEntity>>()

    val orders : LiveData<List<OrderEntity>> = _orders

    suspend fun getAllPendingOrders() {
        _orders.value = orderRepository.getAllPending()
    }

    // add order

    private val _clients = MutableLiveData<List<ClientEntity>>()
    private val _products = MutableLiveData<List<ProductEntity>>()

    private val _clientId = MutableLiveData<Int>()
    private val _productsForOrder = MutableLiveData<List<ProductEntity>>()
    private val _date = MutableLiveData<Long>()

    val clients : LiveData<List<ClientEntity>> = _clients
    val products : LiveData<List<ProductEntity>> = _products

    val clientId : LiveData<Int> = _clientId
    val date : LiveData<Long> = _date
    val productsForOrder : LiveData<List<ProductEntity>> = _productsForOrder

    fun onChangeClientId(value: Int) {
        _clientId.value = value
    }

    fun onChangeDate(value: Long){
        _date.value = value
    }

    suspend fun getAllClients() {
        _clients.value = clientRepository.getAllClients()
    }

    suspend fun getAllProducts() {
        _products.value = productsRepository.getAllProducts()
    }
    suspend fun insertOrder(order: OrderEntity) {
        orderRepository.insertOrder(order)
    }


}