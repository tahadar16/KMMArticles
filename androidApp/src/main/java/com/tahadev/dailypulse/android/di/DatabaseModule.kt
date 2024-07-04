package com.tahadev.dailypulse.android.di

import com.tahadev.dailypulse.articles.data.local.DatabaseDriverFactory
import com.tahadev.dailypulse.db.DailyPulseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseDriverFactory(androidContext()).create() }
    single { DailyPulseDatabase(get()) }
}