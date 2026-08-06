package com.example.restaurantapp.data.repository

import com.example.restaurantapp.data.local.dao.ClienteDao
import com.example.restaurantapp.data.local.toDomain
import com.example.restaurantapp.data.local.toEntity
import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.repository.ClienteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClienteRepositoryImpl(private val dao: ClienteDao) : ClienteRepository {

    override fun listar(): Flow<List<Cliente>> {
        return dao.listar().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun obtenerPorId(id: Long): Cliente? {
        return dao.obtenerPorId(id)?.toDomain()
    }

    override suspend fun registrar(cliente: Cliente): Long {
        return dao.insertar(cliente.toEntity())
    }

    override suspend fun modificar(cliente: Cliente) {
        dao.actualizar(cliente.toEntity())
    }

    override suspend fun eliminar(cliente: Cliente) {
        dao.eliminar(cliente.toEntity())
    }

    override suspend fun buscar(texto: String): List<Cliente> {
        return dao.buscar(texto).map { it.toDomain() }
    }
}
