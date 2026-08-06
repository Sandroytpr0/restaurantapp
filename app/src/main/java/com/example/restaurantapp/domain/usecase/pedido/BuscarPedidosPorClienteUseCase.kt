package com.example.restaurantapp.domain.usecase.pedido

import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.repository.PedidoRepository

/**
 * Caso de uso específico e indispensable solicitado por la interfaz del repositorio
 * para filtrar el historial de pedidos de un cliente específico.
 */
class BuscarPedidosPorClienteUseCase(private val repository: PedidoRepository) {
    suspend operator fun invoke(idCliente: Long): List<Pedido> = repository.buscarPorCliente(idCliente)
}
