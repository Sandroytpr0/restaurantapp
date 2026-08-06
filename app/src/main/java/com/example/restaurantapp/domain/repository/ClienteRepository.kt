package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.model.Cliente
import kotlinx.coroutines.flow.Flow

interface ClienteRepository {
    fun listar(): Flow<List<Cliente>>
    suspend fun obtenerPorId(id: Long): Cliente?
    suspend fun registrar(cliente: Cliente): Long
    suspend fun modificar(cliente: Cliente)
    suspend fun eliminar(cliente: Cliente)
    suspend fun buscar(texto: String): List<Cliente>
}