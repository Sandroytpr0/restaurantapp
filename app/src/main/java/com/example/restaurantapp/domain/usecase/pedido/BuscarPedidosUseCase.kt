package com.example.restaurantapp.domain.usecase.pedido

import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.repository.PedidoRepository

class BuscarPedidosUseCase(private val repository: PedidoRepository) {
    suspend operator fun invoke(texto: String): List<Pedido> = repository.buscar(texto)
}
