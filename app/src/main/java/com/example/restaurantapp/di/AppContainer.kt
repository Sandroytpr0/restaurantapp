package com.example.restaurantapp.di

import android.content.Context
import com.example.restaurantapp.data.local.AppDatabase
import com.example.restaurantapp.data.repository.ClienteRepositoryImpl
import com.example.restaurantapp.data.repository.PedidoRepositoryImpl
import com.example.restaurantapp.data.repository.ProductoRepositoryImpl
import com.example.restaurantapp.domain.repository.ClienteRepository
import com.example.restaurantapp.domain.repository.PedidoRepository
import com.example.restaurantapp.domain.repository.ProductoRepository

class AppContainer(context: Context) {

    private val database = AppDatabase.getDatabase(context)

    val clienteRepository: ClienteRepository by lazy { ClienteRepositoryImpl(database.clienteDao()) }
    val productoRepository: ProductoRepository by lazy { ProductoRepositoryImpl(database.productoDao()) }
    val pedidoRepository: PedidoRepository by lazy { PedidoRepositoryImpl(database.pedidoDao()) }
}
