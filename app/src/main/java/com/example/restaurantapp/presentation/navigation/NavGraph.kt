package com.example.restaurantapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantapp.di.AppContainer
import com.example.restaurantapp.presentation.viewmodel.ViewModelFactory
import com.example.restaurantapp.presentation.viewmodel.ClienteViewModel
import com.example.restaurantapp.presentation.viewmodel.ProductoViewModel
import com.example.restaurantapp.presentation.viewmodel.PedidoViewModel
import com.example.restaurantapp.presentation.screens.*

@Composable
fun NavGraph(navController: NavHostController, appContainer: AppContainer) {

    val factory = ViewModelFactory(appContainer)


    val clienteViewModel: ClienteViewModel = viewModel(factory = factory)
    val productoViewModel: ProductoViewModel = viewModel(factory = factory)
    val pedidoViewModel: PedidoViewModel = viewModel(factory = factory)


    NavHost(navController = navController, startDestination = Screen.Clientes.route) {
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
