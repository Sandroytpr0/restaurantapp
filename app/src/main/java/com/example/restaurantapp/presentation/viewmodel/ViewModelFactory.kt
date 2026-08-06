package com.example.restaurantapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.di.AppContainer
import com.example.restaurantapp.domain.usecase.cliente.*
import com.example.restaurantapp.domain.usecase.producto.*
import com.example.restaurantapp.domain.usecase.pedido.*

class ViewModelFactory(private val container: AppContainer) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ClienteViewModel::class.java) -> {
                ClienteViewModel(
                    ListarClientesUseCase(container.clienteRepository),
                    RegistrarClienteUseCase(container.clienteRepository),
                    ModificarClienteUseCase(container.clienteRepository),
                    EliminarClienteUseCase(container.clienteRepository),
                    BuscarClientesUseCase(container.clienteRepository)
                ) as T
            }
            modelClass.isAssignableFrom(ProductoViewModel::class.java) -> {
                ProductoViewModel(
                    ListarProductosUseCase(container.productoRepository),
                    RegistrarProductoUseCase(container.productoRepository),
                    ModificarProductoUseCase(container.productoRepository),
                    EliminarProductoUseCase(container.productoRepository),
                    BuscarProductosUseCase(container.productoRepository)
                ) as T
            }
            modelClass.isAssignableFrom(PedidoViewModel::class.java) -> {
                PedidoViewModel(
                    ListarPedidosUseCase(container.pedidoRepository),
                    RegistrarPedidoUseCase(container.pedidoRepository),
                    ModificarPedidoUseCase(container.pedidoRepository),
                    EliminarPedidoUseCase(container.pedidoRepository),
                    BuscarPedidosUseCase(container.pedidoRepository),
                    BuscarPedidosPorClienteUseCase(container.pedidoRepository)
                ) as T
            }
            else -> throw IllegalArgumentException("ViewModel desconocido")
        }
    }
}
