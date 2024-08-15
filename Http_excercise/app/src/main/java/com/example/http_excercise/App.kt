package com.example.http_excercise

import android.app.Application
import com.example.http_excercise.data.AppContainer
import com.example.http_excercise.data.DefaultAppContainer

class App : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }

}