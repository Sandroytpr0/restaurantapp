package com.example.restaurantapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restaurantapp.data.local.dao.ClienteDao
import com.example.restaurantapp.data.local.dao.ProductoDao
import com.example.restaurantapp.data.local.dao.PedidoDao
import com.example.restaurantapp.data.local.entity.ClienteEntity
import com.example.restaurantapp.data.local.entity.ProductoEntity
import com.example.restaurantapp.data.local.entity.PedidoEntity

//  Declaramos las 3 entidades obligatorias del examen profesor y la versión de la base de datos
@Database(
    entities = [
        ClienteEntity::class,
        ProductoEntity::class,
        PedidoEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    //  Exponemos los DAOs para que puedan ser usados por los repositorios
    abstract fun clienteDao(): ClienteDao
    abstract fun productoDao(): ProductoDao
    abstract fun pedidoDao(): PedidoDao

    //  Creamos el Singleton para asegurar que solo exista una instancia de la base de datos
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "restaurant_database" // Nombre del archivo SQLite local
                )
                    // Estrategia de migración simple para desarrollo (borra y recrea si cambias las tablas)
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
