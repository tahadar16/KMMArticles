package com.tahadev.dailypulse.di

import com.tahadev.dailypulse.articles.presentation.ArticleViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(sharedKoinModules + databaseModule)
    }
}

class ArticlesInjector : KoinComponent {
    val articleViewModel: ArticleViewModel by inject()
}