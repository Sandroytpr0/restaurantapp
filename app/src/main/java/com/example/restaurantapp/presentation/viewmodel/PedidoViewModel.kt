package com.example.restaurantapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.model.EstadoPedido
import com.example.restaurantapp.domain.usecase.pedido.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel para controlar las comandas o pedidos asignados a las mesas.
 */
class PedidoViewModel(
    private val listarPedidosUseCase: ListarPedidosUseCase,
    private val registrarPedidoUseCase: RegistrarPedidoUseCase,
    private val modificarPedidoUseCase: ModificarPedidoUseCase,
    private val eliminarPedidoUseCase: EliminarPedidoUseCase,
    private val buscarPedidosUseCase: BuscarPedidosUseCase,
    private val buscarPedidosPorClienteUseCase: BuscarPedidosPorClienteUseCase
) : ViewModel() {

    private val _pedidos = MutableStateFlow<List<Pedido>>(emptyList())
    val pedidos: StateFlow<List<Pedido>> = _pedidos.asStateFlow()

    init {
        listarPedidos()
    }

    // 1. LISTAR
    fun listarPedidos() {
        viewModelScope.launch {
            listarPedidosUseCase().collect { lista ->
                _pedidos.value = lista
            }
        }
    }

    // 2. REGISTRAR
    fun registrarPedido(clienteId: Long, productoId: Long, mesa: Int, cantidad: Int, precioUnitario: Double) {
        viewModelScope.launch {
            val nuevoPedido = Pedido(
                clienteId = clienteId,
                productoId = productoId,
                mesa = mesa,
                cantidad = cantidad,
                estado = EstadoPedido.PENDIENTE,
                total = precioUnitario * cantidad
            )
            registrarPedidoUseCase(nuevoPedido)
        }
    }

    // 3. MODIFICAR
    fun modificarPedido(pedido: Pedido) {
        viewModelScope.launch {
            modificarPedidoUseCase(pedido)
        }
    }

    // 4. ELIMINAR
    fun eliminarPedido(pedido: Pedido) {
        viewModelScope.launch {
            eliminarPedidoUseCase(pedido)
        }
    }

    // 5. BUSCAR
    fun buscarPedidos(texto: String) {
        viewModelScope.launch {
            if (texto.isEmpty()) {
                listarPedidos()
            } else {
                _pedidos.value = buscarPedidosUseCase(texto)
            }
        }
    }

    // 6. BUSCAR POR CLIENTE (Función adicional del repositorio)
    fun buscarPorCliente(idCliente: Long) {
        viewModelScope.launch {
            _pedidos.value = buscarPedidosPorClienteUseCase(idCliente)
        }
    }
}
