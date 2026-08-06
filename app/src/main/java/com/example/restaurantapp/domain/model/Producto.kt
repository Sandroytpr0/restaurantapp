package com.example.restaurantapp.domain.model

data class Producto(
    val id: Long = 0L,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val disponible: Boolean = true
)