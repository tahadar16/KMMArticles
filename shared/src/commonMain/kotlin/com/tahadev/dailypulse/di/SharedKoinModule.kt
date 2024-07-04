package com.tahadev.dailypulse.di

import com.tahadev.dailypulse.articles.di.articlesModule

val sharedKoinModules = listOf(
    articlesModule,
    networkModule
)