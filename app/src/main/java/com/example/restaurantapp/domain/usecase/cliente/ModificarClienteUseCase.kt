package com.example.restaurantapp.domain.usecase.cliente

import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.repository.ClienteRepository

/**
 * Caso de uso para actualizar los datos existentes de un cliente (CRUD: Modificar).
 */
class ModificarClienteUseCase(private val repository: ClienteRepository) {
    suspend operator fun invoke(cliente: Cliente) = repository.modificar(cliente)
}
