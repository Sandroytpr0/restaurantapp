package com.example.restaurantapp.data.local.dao

import androidx.room.*
import com.example.restaurantapp.data.local.entity.ProductoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Query("SELECT * FROM productos ORDER BY nombre ASC")
    fun listar(): Flow<List<ProductoEntity>>

    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun obtenerPorId(id: Long): ProductoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(producto: ProductoEntity): Long

    @Update
    suspend fun actualizar(producto: ProductoEntity)

    @Delete
    suspend fun eliminar(producto: ProductoEntity)

    // CORRECCIÓN CRÍTICA: Cambiado 'FROM clientes' a 'FROM productos'
    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :texto || '%'")
    suspend fun buscar(texto: String): List<ProductoEntity>
}
