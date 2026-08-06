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
import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.presentation.viewmodel.ClienteViewModel

@Composable
fun ClienteScreen(viewModel: ClienteViewModel) {
    val clientes by viewModel.clientes.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var buscarTexto by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Registrar Cliente", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = telefono, onValueChange = { telefono = it }, label = { Text("Teléfono") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") }, modifier = Modifier.fillMaxWidth())

        Button(
            onClick = {
                if (nombre.isNotBlank() && telefono.isNotBlank() && direccion.isNotBlank()) {
                    viewModel.registrarCliente(nombre, telefono, direccion)
                    nombre = ""; telefono = ""; direccion = ""
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Guardar Cliente")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        OutlinedTextField(
            value = buscarTexto,
            onValueChange = {
                buscarTexto = it
                viewModel.buscarClientes(it)
            },
            label = { Text("Buscar Cliente por Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 8.dp)) {
            items(clientes) { cliente ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(cliente.nombre, style = MaterialTheme.typography.bodyLarge)
                            Text("Telf: ${cliente.telefono} | Dir: ${cliente.direccion}", style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = { viewModel.eliminarCliente(cliente) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }
    }
}
