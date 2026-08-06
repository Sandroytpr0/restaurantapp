package com.example.restaurantapp.domain.usecase.producto

import com.example.restaurantapp.domain.model.Producto
import com.example.restaurantapp.domain.repository.ProductoRepository

class EliminarProductoUseCase(private val repository: ProductoRepository) {
    suspend operator fun invoke(producto: Producto) = repository.eliminar(producto)
}
