package com.tahadev.dailypulse.android.di

import com.tahadev.dailypulse.articles.presentation.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModeModule = module {
    viewModel { ArticleViewModel(get()) }
}