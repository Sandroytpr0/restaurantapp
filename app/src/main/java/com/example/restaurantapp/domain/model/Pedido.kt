package com.example.restaurantapp.domain.model

data class Pedido(
    val id: Long = 0L,
    val clienteId: Long,
    val productoId: Long,
    val mesa: Int,
    val cantidad: Int,
    val estado: EstadoPedido = EstadoPedido.PENDIENTE,
    val fecha: Long = System.currentTimeMillis(),
    val total: Double
)

enum class EstadoPedido(val etiqueta: String) {
    PENDIENTE("Pendiente"),
    EN_PREPARACION("En preparación"),
    ENTREGADO("Entregado"),
    CANCELADO("Cancelado")
}