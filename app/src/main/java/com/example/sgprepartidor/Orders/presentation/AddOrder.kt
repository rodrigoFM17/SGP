package com.example.sgprepartidor.Orders.presentation

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.sgprepartidor.components.ButtonComponent
import com.example.sgprepartidor.layouts.Container
import kotlinx.coroutines.launch
import java.util.Calendar
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun AddOrder(ordersViewModel: OrdersViewModel){

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDate by remember { mutableStateOf("") }
    var timestamp by remember { mutableStateOf<Long?>(null) } // Guarda el timestamp

    var showDialog by remember { mutableStateOf(false) }
    var showMenu by remember { mutableStateOf(false) }

    val clients by ordersViewModel.clients.observeAsState(emptyList())
    val products by ordersViewModel.products.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        ordersViewModel.viewModelScope.launch {
            ordersViewModel.getAllClients()
            ordersViewModel.getAllProducts()
        }
    }

    Container(
        headerTitle = "Agregar Pedido"
    ) {

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            clients.forEach {client ->
                DropdownMenuItem(
                    text = { Text(text = client.name)},
                    onClick = {
                        ordersViewModel.onChangeClientId(client.id)
                    }
                )
            }

        }

        if (showDialog){
            DatePickerDialog(
                context,
                {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    calendar.set(year, month, dayOfMonth, 0, 0, 0) // Establecer la fecha seleccionada en el calendario
                    timestamp = calendar.timeInMillis // Convertir a timestamp
                    ordersViewModel.onChangeDate(calendar.timeInMillis)
                    selectedDate = "$dayOfMonth/${month + 1}/$year"
                    showDialog = false
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        Row {
            ButtonComponent(
                icon = Icons.Default.CalendarMonth,
                onClick = { showDialog = true}
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text( text = selectedDate)
        }

        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            items(products) {
                product ->
                Row {
                    Text( text = product.name, modifier = Modifier.weight(2f))
                    Text(text = product.price.toString(), modifier = Modifier.weight(1f))
                }
            }

        }

    }
}