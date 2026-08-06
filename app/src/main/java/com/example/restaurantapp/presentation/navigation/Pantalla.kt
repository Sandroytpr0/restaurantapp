package com.example.restaurantapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Pantalla(val ruta: String, val titulo: String, val icono: ImageVector) {
    data object Inicio : Pantalla("inicio", "Inicio", Icons.Filled.Home)
    data object Clientes : Pantalla("clientes", "Clientes", Icons.Filled.Person)
    data object Productos : Pantalla("productos", "Productos", Icons.Filled.RestaurantMenu)
    data object Pedidos : Pantalla("pedidos", "Pedidos", Icons.Filled.Receipt)

    companion object {
        val itemsMenu = listOf(Inicio, Clientes, Productos, Pedidos)
    }
}