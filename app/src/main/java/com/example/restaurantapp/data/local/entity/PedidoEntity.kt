package com.example.restaurantapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos") // ¡Esto le dice a Room que la tabla se llama 'pedidos'!
data class PedidoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val clienteId: Long,
    val productoId: Long,
    val mesa: Int,
    val cantidad: Int,
    val estado: String, // "Pendiente", "En preparación", etc.
    val fecha: Long,    // Guardado como timestamp (Long)
    val total: Double
)
