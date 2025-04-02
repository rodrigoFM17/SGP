package com.example.sgprepartidor.Products.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.sgprepartidor.components.TextFieldComponent
import com.example.sgprepartidor.layouts.Container
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.sgprepartidor.components.ButtonComponent
import com.example.sgprepartidor.core.data.local.Product.entities.ProductEntity
import com.example.sgprepartidor.core.data.local.Product.entities.UNIT
import kotlinx.coroutines.launch
import kotlin.math.exp

@Composable
fun AddProductScreen( productsViewModel: ProductsViewModel) {

    var expanded by remember { mutableStateOf(false) }

    val name by productsViewModel.name.observeAsState("")
    val price by productsViewModel.price.observeAsState()
    val unit by productsViewModel.unit.observeAsState()

    Container(
        headerTitle = "Agregar un producto"
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            TextFieldComponent(
                value = name,
                onValueChange = productsViewModel::onChangeName,
                placeholder = "Nombre del producto"
            )

            TextFieldComponent(
                value = price.toString(),
                onValueChange = productsViewModel::onChangePrice,
                placeholder = "Precio del producto"
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text( text = UNIT.INT.toString()) },
                    onClick = {
                        productsViewModel.onChangeUnit(UNIT.INT)
                    }
                )
                DropdownMenuItem(
                    text = { Text( text = UNIT.FRACC.toString()) },
                    onClick = {
                        productsViewModel.onChangeUnit(UNIT.FRACC)
                    }
                )
            }

            ButtonComponent(
                onClick = {
                    productsViewModel.viewModelScope.launch {
                        productsViewModel.insertProduct(ProductEntity(
                            name = name,
                            price = price!!,
                            unit = unit!!
                        ))
                    }
                },
                text = "Guardar"
            )
        }
    }

}