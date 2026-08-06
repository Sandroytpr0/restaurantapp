package com.example.restaurantapp.domain.usecase.pedido

import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.repository.PedidoRepository

class ModificarPedidoUseCase(private val repository: PedidoRepository) {
    suspend operator fun invoke(pedido: Pedido) = repository.modificar(pedido)
}
