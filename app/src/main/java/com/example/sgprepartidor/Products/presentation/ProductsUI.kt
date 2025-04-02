package com.example.sgprepartidor.Products.presentation

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.sgprepartidor.Products.presentation.components.ProductCard
import com.example.sgprepartidor.components.ButtonComponent
import com.example.sgprepartidor.layouts.Container
import kotlinx.coroutines.launch

@Composable
fun ProductsScreen(productsViewModel: ProductsViewModel) {

    val products by productsViewModel.products.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        productsViewModel.viewModelScope.launch {
            productsViewModel.getAllProducts()
        }
    }
    Container(
        headerTitle = "Productos"
    ) {
        if(products.isEmpty()){
            Text( text = "Aun no ha registrado ningun producto")
        }

        ButtonComponent(
            onClick = productsViewModel.navigateToAddProduct,
            icon = Icons.Default.Add,
            text = "Agregar un producto"
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(products) { product ->
                ProductCard(product)
            }

        }

    }
}