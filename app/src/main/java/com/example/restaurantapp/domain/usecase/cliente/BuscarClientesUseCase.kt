package com.example.restaurantapp.domain.usecase.cliente

import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.repository.ClienteRepository

/**
 * Caso de uso para buscar clientes mediante coincidencias de texto en su nombre (CRUD: Buscar).
 */
class BuscarClientesUseCase(private val repository: ClienteRepository) {
    suspend operator fun invoke(texto: String): List<Cliente> = repository.buscar(texto)
}
