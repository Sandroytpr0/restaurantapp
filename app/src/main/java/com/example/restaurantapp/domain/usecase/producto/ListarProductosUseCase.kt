package com.example.restaurantapp.domain.usecase.producto

import com.example.restaurantapp.domain.model.Producto
import com.example.restaurantapp.domain.repository.ProductoRepository
import kotlinx.coroutines.flow.Flow

class ListarProductosUseCase(private val repository: ProductoRepository) {
    operator fun invoke(): Flow<List<Producto>> = repository.listar()
}
