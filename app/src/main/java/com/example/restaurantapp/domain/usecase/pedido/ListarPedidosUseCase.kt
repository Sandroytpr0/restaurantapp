package com.example.restaurantapp.domain.usecase.pedido

import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.repository.PedidoRepository
import kotlinx.coroutines.flow.Flow

class ListarPedidosUseCase(private val repository: PedidoRepository) {
    operator fun invoke(): Flow<List<Pedido>> = repository.listar()
}
