package com.example.restaurantapp.domain.usecase.cliente

import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.repository.ClienteRepository

/**
 * Caso de uso para registrar un nuevo cliente en el sistema del restaurante.
 */
class RegistrarClienteUseCase(private val repository: ClienteRepository) {
    suspend operator fun invoke(cliente: Cliente): Long = repository.registrar(cliente)
}
