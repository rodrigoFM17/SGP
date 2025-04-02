package com.example.sgprepartidor.Products.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sgprepartidor.core.data.local.Product.entities.ProductEntity

class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<List<ProductEntity>>()

    val products: LiveData<List<ProductEntity>> = _products


}