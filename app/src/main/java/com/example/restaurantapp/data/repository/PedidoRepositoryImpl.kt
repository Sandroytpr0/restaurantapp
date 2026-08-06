package com.example.restaurantapp.data.repository

import com.example.restaurantapp.data.local.dao.PedidoDao
import com.example.restaurantapp.data.local.toDomain
import com.example.restaurantapp.data.local.toEntity
import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.repository.PedidoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PedidoRepositoryImpl(private val dao: PedidoDao) : PedidoRepository {

    override fun listar(): Flow<List<Pedido>> {
        return dao.listar().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun obtenerPorId(id: Long): Pedido? {
        return dao.obtenerPorId(id)?.toDomain()
    }

    override suspend fun registrar(pedido: Pedido): Long {
        return dao.insertar(pedido.toEntity())
    }

    override suspend fun modificar(pedido: Pedido) {
        dao.actualizar(pedido.toEntity())
    }

    override suspend fun eliminar(pedido: Pedido) {
        dao.eliminar(pedido.toEntity())
    }

    override suspend fun buscar(texto: String): List<Pedido> {
        return dao.buscar(texto).map { it.toDomain() }

    }


    override suspend fun buscarPorCliente(idCliente: Long): List<Pedido> {
        return dao.buscarPorCliente(idCliente).map { it.toDomain() }
    }

}
