package com.tahadev.dailypulse.di

import com.tahadev.dailypulse.articles.data.local.DatabaseDriverFactory
import com.tahadev.dailypulse.db.DailyPulseDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseDriverFactory().create() }
    single { DailyPulseDatabase(get()) }
}