


 Restaurant Management App

 Sistema Android de Gestión de Restaurantes

Kotlin · Jetpack Compose · Material 3 · MVVM

Proyecto académico de desarrollo de aplicaciones móviles.


 Descripción

Restaurant Management App es una aplicación Android
desarrollada con Kotlin y Jetpack Compose, orientada
a la gestión de pedidos de un restaurante.

Permite seleccionar clientes y productos, registrar
comandas por mesa y consultar los pedidos registrados.

 Funcionalidades

- Selección de clientes registrados.
- Selección de platos y bebidas.
- Registro de pedidos por número de mesa.
- Registro de cantidades.
- Asociación de pedidos con clientes y productos.
- Visualización del importe total.
- Consulta del estado de cada pedido.
- Listado de pedidos.
- Eliminación de pedidos.
- Validación básica de datos de entrada.

 Tecnologías

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- Android ViewModel
- StateFlow
- Kotlin Coroutines (pendiente de verificar)

Organización del proyecto

restaurantapp/
├── data/
├── di/
├── domain/
├── presentation/
│   ├── navigation/
│   ├── screens/
│   │   ├── InicioScreen.kt
│   │   ├── ClienteScreen.kt
│   │   ├── ProductoScreen.kt
│   │   └── PedidoScreen.kt
│   └── viewmodel/
├── ui/
│   └── theme/
├── MainActivity.kt
└── RestaurantApp.kt


El proyecto separa las responsabilidades de
presentación, dominio y acceso a datos.

La interfaz utiliza ViewModels para gestionar
el estado de clientes, productos y pedidos.

 Pantallas identificadas

- Inicio
- Clientes
- Productos
- Pedidos

 Ejecución

1. Clonar el repositorio.
2. Abrir el proyecto en Android Studio.
3. Sincronizar Gradle.
4. Seleccionar un emulador o dispositivo compatible.
5. Compilar y ejecutar la aplicación.

 Contexto académico

Proyecto desarrollado durante mi formación
en Ingeniería de Software en ISIL.

Autor

Sandro Callañaupa Portal

Estudiante de Ingeniería de Software — ISIL

[GitHub](https://github.com/Sandroytpr0)


Proyecto incluido en mi portafolio académico.
