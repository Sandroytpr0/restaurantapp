package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.model.Pedido
import kotlinx.coroutines.flow.Flow

interface PedidoRepository {
    fun listar(): Flow<List<Pedido>>
    suspend fun obtenerPorId(id: Long): Pedido?
    suspend fun registrar(pedido: Pedido): Long
    suspend fun modificar(pedido: Pedido)
    suspend fun eliminar(pedido: Pedido)
    suspend fun buscar(texto: String): List<Pedido>
    suspend fun buscarPorCliente(idCliente: Long) : List <Pedido>
}