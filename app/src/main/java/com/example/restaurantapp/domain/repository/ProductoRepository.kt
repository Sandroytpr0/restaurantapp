package com.example.restaurantapp.domain.repository

import com.example.restaurantapp.domain.model.Producto
import kotlinx.coroutines.flow.Flow

interface ProductoRepository {
    fun listar(): Flow<List<Producto>>
    suspend fun obtenerPorId(id: Long): Producto?
    suspend fun registrar(producto: Producto): Long
    suspend fun modificar(producto: Producto)
    suspend fun eliminar(producto: Producto)
    suspend fun buscar(texto: String): List<Producto>
}