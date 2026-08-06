package com.example.restaurantapp.data.local.dao

import androidx.room.*
import com.example.restaurantapp.data.local.entity.PedidoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {
    // CORRECCIÓN: Ordenamos por 'fecha' de forma descendente (los más recientes primero)
    @Query("SELECT * FROM pedidos ORDER BY fecha DESC")
    fun listar(): Flow<List<PedidoEntity>>

    @Query("SELECT * FROM pedidos WHERE id = :id")
    suspend fun obtenerPorId(id: Long): PedidoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(pedido: PedidoEntity): Long

    @Update
    suspend fun actualizar(pedido: PedidoEntity)

    @Delete
    suspend fun eliminar(pedido: PedidoEntity)


    @Query("SELECT * FROM pedidos WHERE estado LIKE '%' || :texto || '%' OR CAST(mesa AS TEXT) LIKE '%' || :texto || '%'")
    suspend fun buscar(texto: String): List<PedidoEntity>


    @Query("SELECT * FROM pedidos WHERE clienteId = :idCliente")
    suspend fun buscarPorCliente(idCliente: Long): List<PedidoEntity>

}
