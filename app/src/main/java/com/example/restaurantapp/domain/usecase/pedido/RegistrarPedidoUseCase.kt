package com.example.restaurantapp.domain.usecase.pedido

import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.repository.PedidoRepository

class RegistrarPedidoUseCase(private val repository: PedidoRepository) {
    suspend operator fun invoke(pedido: Pedido): Long = repository.registrar(pedido)
}
