package com.example.restaurantapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Clientes : Screen("clientes", "Clientes", Icons.Default.Person)
    object Productos : Screen("productos", "Productos", Icons.Default.List)
    object Pedidos : Screen("pedidos", "Pedidos", Icons.Default.ShoppingCart)
}
