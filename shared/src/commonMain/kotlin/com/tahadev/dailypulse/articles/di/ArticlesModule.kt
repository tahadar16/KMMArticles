package com.tahadev.dailypulse.articles.di

import com.tahadev.dailypulse.articles.data.local.ArticleDataSource
import com.tahadev.dailypulse.articles.data.repository.ArticlesRepoImpl
import com.tahadev.dailypulse.articles.domain.repository.ArticlesRepo
import com.tahadev.dailypulse.articles.domain.use_case.FetchArticlesUsecase
import com.tahadev.dailypulse.articles.presentation.ArticleViewModel
import org.koin.dsl.module

val articlesModule = module {
    single<ArticlesRepo> { ArticlesRepoImpl(get(), get()) }
    single { FetchArticlesUsecase(get()) }
    single { ArticleViewModel(get()) }
    single { ArticleDataSource(get()) }
}