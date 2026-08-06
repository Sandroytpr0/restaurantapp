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
import com.example.restaurantapp.presentation.viewmodel.PedidoViewModel
import com.example.restaurantapp.presentation.viewmodel.ClienteViewModel
import com.example.restaurantapp.presentation.viewmodel.ProductoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedidoScreen(
    pedidoViewModel: PedidoViewModel,
    clienteViewModel: ClienteViewModel,
    productoViewModel: ProductoViewModel
) {
    val pedidos by pedidoViewModel.pedidos.collectAsState()
    val clientes by clienteViewModel.clientes.collectAsState()
    val productos by productoViewModel.productos.collectAsState()

    var mesa by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }

    var clienteSeleccionado by remember { mutableStateOf<com.example.restaurantapp.domain.model.Cliente?>(null) }
    var productoSeleccionado by remember { mutableStateOf<com.example.restaurantapp.domain.model.Producto?>(null) }
    var examinarClientesExpanded by remember { mutableStateOf(false) }
    var examinarProductosExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Generar Comanda / Pedido", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(expanded = examinarClientesExpanded, onExpandedChange = { examinarClientesExpanded = !examinarClientesExpanded }) {
            OutlinedTextField(
                value = clienteSeleccionado?.nombre ?: "Seleccionar Cliente",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = examinarClientesExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(expanded = examinarClientesExpanded, onDismissRequest = { examinarClientesExpanded = false }) {
                clientes.forEach { cliente ->
                    DropdownMenuItem(text = { Text(cliente.nombre) }, onClick = {
                        clienteSeleccionado = cliente
                        examinarClientesExpanded = false
                    })
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(expanded = examinarProductosExpanded, onExpandedChange = { examinarProductosExpanded = !examinarProductosExpanded }) {
            OutlinedTextField(
                value = productoSeleccionado?.nombre ?: "Seleccionar Plato/Bebida",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = examinarProductosExpanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(expanded = examinarProductosExpanded, onDismissRequest = { examinarProductosExpanded = false }) {
                productos.forEach { producto ->
                    DropdownMenuItem(text = { Text("${producto.nombre} (S/. ${producto.precio})") }, onClick = {
                        productoSeleccionado = producto
                        examinarProductosExpanded = false
                    })
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = mesa, onValueChange = { mesa = it }, label = { Text("Mesa N°") }, modifier = Modifier.weight(1f))
            OutlinedTextField(value = cantidad, onValueChange = { cantidad = it }, label = { Text("Cantidad") }, modifier = Modifier.weight(1f))
        }

        Button(
            onClick = {
                val cliente = clienteSeleccionado
                val producto = productoSeleccionado
                val mesaInt = mesa.toIntOrNull() ?: 0
                val cantidadInt = cantidad.toIntOrNull() ?: 0

                if (cliente != null && producto != null && mesaInt > 0 && cantidadInt > 0) {
                    pedidoViewModel.registrarPedido(
                        clienteId = cliente.id,
                        productoId = producto.id,
                        mesa = mesaInt,
                        cantidad = cantidadInt,
                        precioUnitario = producto.precio
                    )
                    mesa = ""; cantidad = ""; clienteSeleccionado = null; productoSeleccionado = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar Pedido")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
        Text("Listado de Pedidos Activos", style = MaterialTheme.typography.titleMedium)

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            items(pedidos) { pedido ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("Pedido N° ${pedido.id} - Mesa ${pedido.mesa}", style = MaterialTheme.typography.bodyLarge)
                            Text("Cantidad: ${pedido.cantidad} | Total: S/. ${pedido.total}", style = MaterialTheme.typography.bodyMedium)
                            Text("Estado: ${pedido.estado.etiqueta}", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = { pedidoViewModel.eliminarPedido(pedido) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}
