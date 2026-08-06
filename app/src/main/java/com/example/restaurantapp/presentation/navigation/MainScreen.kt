package com.example.restaurantapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.di.AppContainer


import com.example.restaurantapp.presentation.viewmodel.ViewModelFactory
import com.example.restaurantapp.presentation.viewmodel.ClienteViewModel
import com.example.restaurantapp.presentation.viewmodel.ProductoViewModel
import com.example.restaurantapp.presentation.viewmodel.PedidoViewModel


import com.example.restaurantapp.presentation.screens.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val container = remember { AppContainer(context) }
    val factory = remember { ViewModelFactory(container) }

    // Al solucionar las importaciones de arriba, el tipo se infiere automáticamente
    val clienteViewModel: ClienteViewModel = viewModel(factory = factory)
    val productoViewModel: ProductoViewModel = viewModel(factory = factory)
    val pedidoViewModel: PedidoViewModel = viewModel(factory = factory)

    val items = listOf(
        Screen.Clientes,
        Screen.Productos,
        Screen.Pedidos
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Restaurante App - Control de Pedidos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Clientes.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Clientes.route) {
                ClienteScreen(viewModel = clienteViewModel)
            }
            composable(Screen.Productos.route) {
                ProductoScreen(viewModel = productoViewModel)
            }
            composable(Screen.Pedidos.route) {
                PedidoScreen(
                    pedidoViewModel = pedidoViewModel,
                    clienteViewModel = clienteViewModel,
                    productoViewModel = productoViewModel
                )
            }
        }
    }
}
