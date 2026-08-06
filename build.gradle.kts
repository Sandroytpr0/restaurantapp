plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false // ¡Esto es clave!
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false // Agregar KSP de forma global
}
