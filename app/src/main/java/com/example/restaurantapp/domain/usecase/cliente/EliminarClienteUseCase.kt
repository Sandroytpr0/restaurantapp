package com.example.restaurantapp.domain.usecase.cliente

import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.repository.ClienteRepository

/**
 * Caso de uso para dar de baja o eliminar un cliente del sistema (CRUD: Eliminar).
 */
class EliminarClienteUseCase(private val repository: ClienteRepository) {
    suspend operator fun invoke(cliente: Cliente) = repository.eliminar(cliente)
}
