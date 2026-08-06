package com.example.restaurantapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantapp.domain.model.Producto
import com.example.restaurantapp.presentation.viewmodel.ProductoViewModel

@Composable
fun ProductoScreen(viewModel: ProductoViewModel) {
    val productos by viewModel.productos.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var buscarTexto by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Registrar Producto / Plato", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre del Plato") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = categoria, onValueChange = { categoria = it }, label = { Text("Categoría (Entrada, Fondo, Bebida)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio (S/.)") }, modifier = Modifier.fillMaxWidth())

        Button(
            onClick = {
                val precioDouble = precio.toDoubleOrNull() ?: 0.0
                if (nombre.isNotBlank() && precioDouble > 0.0) {
                    viewModel.registrarProducto(nombre, categoria, precioDouble)
                    nombre = ""; categoria = ""; precio = ""
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Guardar Producto")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = buscarTexto,
            onValueChange = {
                buscarTexto = it
                viewModel.buscarProductos(it)
            },
            label = { Text("Buscar Producto") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            items(productos) { producto ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(producto.nombre, style = MaterialTheme.typography.bodyLarge)
                            Text("${producto.categoria} - S/. ${producto.precio}", style = MaterialTheme.typography.bodyMedium)
                        }
                        IconButton(onClick = { viewModel.eliminarProducto(producto) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}
