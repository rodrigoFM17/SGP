package com.example.sgprepartidor.Products.data.repository

import android.content.Context
import com.example.sgprepartidor.core.data.local.Product.entities.ProductEntity
import com.example.sgprepartidor.core.data.local.appDatabase.DatabaseProvider

class ProductRepository(context: Context) {

    private val productDAO = DatabaseProvider.getDatabase(context).productDAO()

    suspend fun insertProduct(product: ProductEntity){
        productDAO.insertProduct(product)
    }

}