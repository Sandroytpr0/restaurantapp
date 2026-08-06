package com.example.restaurantapp.domain.usecase.pedido

import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.repository.PedidoRepository

class EliminarPedidoUseCase(private val repository: PedidoRepository) {
    suspend operator fun invoke(pedido: Pedido) = repository.eliminar(pedido)
}
