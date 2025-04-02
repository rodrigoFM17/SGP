package com.example.sgprepartidor.Products.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.sgprepartidor.Products.presentation.components.ProductCard
import com.example.sgprepartidor.layouts.Container

@Composable
fun ProductsScreen(productsViewModel: ProductsViewModel) {

    val products by productsViewModel.products.observeAsState(emptyList())
    Container(
        headerTitle = "Productos"
    ) {

        LazyColumn {
            items(products) { product ->
                ProductCard(product)
            }

        }

    }
}