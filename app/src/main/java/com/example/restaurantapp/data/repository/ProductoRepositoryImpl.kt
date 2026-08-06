package com.example.restaurantapp.data.repository

import com.example.restaurantapp.data.local.dao.ProductoDao
import com.example.restaurantapp.data.local.toDomain
import com.example.restaurantapp.data.local.toEntity
import com.example.restaurantapp.domain.model.Producto
import com.example.restaurantapp.domain.repository.ProductoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductoRepositoryImpl(private val dao: ProductoDao) : ProductoRepository {

    override fun listar(): Flow<List<Producto>> {
        return dao.listar().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun obtenerPorId(id: Long): Producto? {
        return dao.obtenerPorId(id)?.toDomain()
    }

    override suspend fun registrar(producto: Producto): Long {
        return dao.insertar(producto.toEntity())
    }

    override suspend fun modificar(producto: Producto) {
        dao.actualizar(producto.toEntity())
    }

    override suspend fun eliminar(producto: Producto) {
        dao.eliminar(producto.toEntity())
    }

    override suspend fun buscar(texto: String): List<Producto> {
        return dao.buscar(texto).map { it.toDomain() }
    }
}
