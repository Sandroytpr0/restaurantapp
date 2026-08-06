package com.example.restaurantapp.domain.usecase.producto

import com.example.restaurantapp.domain.model.Producto
import com.example.restaurantapp.domain.repository.ProductoRepository

class RegistrarProductoUseCase(private val repository: ProductoRepository) {
    suspend operator fun invoke(producto: Producto): Long = repository.registrar(producto)
}
