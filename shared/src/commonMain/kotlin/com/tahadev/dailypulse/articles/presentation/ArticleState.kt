package com.tahadev.dailypulse.articles.presentation

import com.tahadev.dailypulse.articles.domain.model.Article

sealed class ArticleState {
    data class Success(val articles: List<Article>, val isLoading: Boolean = false) : ArticleState()
    data class Loading(val isLoading: Boolean, val articles: List<Article> = emptyList()) : ArticleState()
    data class Error(val errMsg: String?) : ArticleState()
}
