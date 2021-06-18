package com.example.projetoconsultacep

import android.app.Application

class SingletonDataBase : Application() {

    var helper: DataBaseSQLite? = null
        private set

    companion object {
        lateinit var instance: SingletonDataBase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helper = DataBaseSQLite(this)
    }
}