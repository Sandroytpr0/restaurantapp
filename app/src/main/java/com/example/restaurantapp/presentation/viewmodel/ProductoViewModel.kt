package com.example.restaurantapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.model.Producto
import com.example.restaurantapp.domain.usecase.producto.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel encargado del mantenimiento CRUD de los Productos del restaurante.
 */
class ProductoViewModel(
    private val listarProductosUseCase: ListarProductosUseCase,
    private val registrarProductoUseCase: RegistrarProductoUseCase,
    private val modificarProductoUseCase: ModificarProductoUseCase,
    private val eliminarProductoUseCase: EliminarProductoUseCase,
    private val buscarProductosUseCase: BuscarProductosUseCase
) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    init {
        listarProductos()
    }

    // 1. LISTAR
    fun listarProductos() {
        viewModelScope.launch {
            listarProductosUseCase().collect { lista ->
                _productos.value = lista
            }
        }
    }

    // 2. REGISTRAR
    fun registrarProducto(nombre: String, categoria: String, precio: Double) {
        viewModelScope.launch {
            val nuevoProducto = Producto(nombre = nombre, categoria = categoria, precio = precio)
            registrarProductoUseCase(nuevoProducto)
        }
    }

    // 3. MODIFICAR
    fun modificarProducto(producto: Producto) {
        viewModelScope.launch {
            modificarProductoUseCase(producto)
        }
    }

    // 4. ELIMINAR
    fun eliminarProducto(producto: Producto) {
        viewModelScope.launch {
            eliminarProductoUseCase(producto)
        }
    }

    // 5. BUSCAR
    fun buscarProductos(texto: String) {
        viewModelScope.launch {
            if (texto.isEmpty()) {
                listarProductos()
            } else {
                _productos.value = buscarProductosUseCase(texto)
            }
        }
    }
}
