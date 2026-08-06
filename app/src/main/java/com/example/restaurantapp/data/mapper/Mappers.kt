package com.example.restaurantapp.data.local

import com.example.restaurantapp.data.local.entity.ClienteEntity
import com.example.restaurantapp.data.local.entity.ProductoEntity
import com.example.restaurantapp.data.local.entity.PedidoEntity
import com.example.restaurantapp.domain.model.Cliente
import com.example.restaurantapp.domain.model.Producto
import com.example.restaurantapp.domain.model.Pedido
import com.example.restaurantapp.domain.model.EstadoPedido

// aqui esta el codigo de  Mapeos de Cliente profesor
fun ClienteEntity.toDomain(): Cliente = Cliente(id, nombre, telefono, direccion)
fun Cliente.toEntity(): ClienteEntity = ClienteEntity(id, nombre, telefono, direccion)

// aqui esta el codigo de  Mapeos de Producto profesor
fun ProductoEntity.toDomain(): Producto = Producto(id, nombre, categoria, precio, disponible)
fun Producto.toEntity(): ProductoEntity = ProductoEntity(id, nombre, categoria, precio, disponible)

// aqui esta el codigo de  Mapeos de Pedido profesor
fun PedidoEntity.toDomain(): Pedido = Pedido(
    id = id,
    clienteId = clienteId,
    productoId = productoId,
    mesa = mesa,
    cantidad = cantidad,
    estado = try { EstadoPedido.valueOf(estado) } catch (e: Exception) { EstadoPedido.PENDIENTE },
    fecha = fecha,
    total = total
)
fun Pedido.toEntity(): PedidoEntity = PedidoEntity(
    id = id,
    clienteId = clienteId,
    productoId = productoId,
    mesa = mesa,
    cantidad = cantidad,
    estado = estado.name,
    fecha = fecha,
    total = total
)
