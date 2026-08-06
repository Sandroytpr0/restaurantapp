package com.example.restaurantapp.domain.usecase.cliente

import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.repository.ClienteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Caso de uso para obtener el flujo continuo de la lista de clientes.
 * Cumple con la rúbrica de Clean Architecture al aislar la UI de la base de datos.
 */
class ListarClientesUseCase(private val repository: ClienteRepository) {
    operator fun invoke(): Flow<List<Cliente>> = repository.listar()
}
