package com.example.sgprepartidor.Orders.data.repository

import android.content.Context
import com.example.sgprepartidor.core.data.local.Order.entities.OrderEntity
import com.example.sgprepartidor.core.data.local.appDatabase.DatabaseProvider

class OrderRepository(context: Context) {

    private val orderDAO = DatabaseProvider.getDatabase(context).orderDAO()

    suspend fun insertOrder(order: OrderEntity) {
        orderDAO.insertOrder(order)
    }

    suspend fun getAllPending(): List<OrderEntity> {
        return orderDAO.getAllPendingOrders()
    }

    suspend fun getOrderById(id: Int): OrderEntity {
        return orderDAO.getOrderById(id)
    }
}