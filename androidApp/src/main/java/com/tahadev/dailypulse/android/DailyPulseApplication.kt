package com.tahadev.dailypulse.android

import android.app.Application
import com.tahadev.dailypulse.android.di.databaseModule
import com.tahadev.dailypulse.android.di.viewModeModule
import com.tahadev.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@DailyPulseApplication)
            val modules = sharedKoinModules + viewModeModule + databaseModule
            modules(modules)
        }
    }
}