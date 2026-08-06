package com.example.restaurantapp

import android.app.Application
import com.example.restaurantapp.di.AppContainer

class RestaurantApp : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}