package com.example.restaurantapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.usecase.cliente.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para gestionar el estado y operaciones CRUD de Clientes.
 * Emplea StateFlow para actualizar la interfaz de Jetpack Compose en tiempo real.
 */
class ClienteViewModel(
    private val listarClientesUseCase: ListarClientesUseCase,
    private val registrarClienteUseCase: RegistrarClienteUseCase,
    private val modificarClienteUseCase: ModificarClienteUseCase,
    private val eliminarClienteUseCase: EliminarClienteUseCase,
    private val buscarClientesUseCase: BuscarClientesUseCase
) : ViewModel() {

    // Estado que contiene la lista de clientes expuesta a la UI
    private val _clientes = MutableStateFlow<List<Cliente>>(emptyList())
    val clientes: StateFlow<List<Cliente>> = _clientes.asStateFlow()

    init {
        // Inicializa el listado automático de clientes al abrir la pantalla
        listarClientes()
    }

    // 1. LISTAR
    fun listarClientes() {
        viewModelScope.launch {
            listarClientesUseCase().collect { lista ->
                _clientes.value = lista
            }
        }
    }

    // 2. REGISTRAR
    fun registrarCliente(nombre: String, telefono: String, direccion: String) {
        viewModelScope.launch {
            val nuevoCliente = Cliente(nombre = nombre, telefono = telefono, direccion = direccion)
            registrarClienteUseCase(nuevoCliente)
        }
    }

    // 3. MODIFICAR
    fun modificarCliente(cliente: Cliente) {
        viewModelScope.launch {
            modificarClienteUseCase(cliente)
        }
    }

    // 4. ELIMINAR
    fun eliminarCliente(cliente: Cliente) {
        viewModelScope.launch {
            eliminarClienteUseCase(cliente)
        }
    }

    // 5. BUSCAR
    fun buscarClientes(texto: String) {
        viewModelScope.launch {
            if (texto.isEmpty()) {
                listarClientes() // Si el buscador está vacío, muestra todos
            } else {
                _clientes.value = buscarClientesUseCase(texto)
            }
        }
    }
}
