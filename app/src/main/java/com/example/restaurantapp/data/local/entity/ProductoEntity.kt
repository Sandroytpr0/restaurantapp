package com.example.restaurantapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos") // ¡Esto le dice a Room que la tabla se llama 'productos'!
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nombre: String,           // ¡Esto resuelve el error de 'nombre'!
    val categoria: String,
    val precio: Double,
    val disponible: Boolean = true
)
