package com.example.restaurantapp.domain.model

data class Cliente(
    val id: Long = 0L,
    val nombre: String,
    val telefono: String,
    val direccion: String
)