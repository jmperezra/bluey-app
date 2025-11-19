package com.jmperezra.bluey.core

import android.app.Application
import com.jmperezra.bluey.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BlueyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BlueyApplication)
            modules(
                appModule
            )
        }
    }
}