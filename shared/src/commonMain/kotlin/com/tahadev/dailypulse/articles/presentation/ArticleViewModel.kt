package com.tahadev.dailypulse.articles.presentation

import com.tahadev.dailypulse.BaseViewModel
import com.tahadev.dailypulse.articles.data.repository.ArticlesRepoImpl
import com.tahadev.dailypulse.articles.domain.model.Article
import com.tahadev.dailypulse.articles.domain.use_case.FetchArticlesUsecase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticleViewModel(
    private val fetchArticlesUsecase: FetchArticlesUsecase
) : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticleState> =
        MutableStateFlow(ArticleState.Loading(false))
    val articlesState: StateFlow<ArticleState> get() = _articlesState

    init {
        loadData()
    }

    fun loadData(forceLoad: Boolean = false) {
        scope.launch {
            _articlesState.emit(ArticleState.Loading(true))

            val fetchedArticles = fetchArticlesUsecase.fetchArticles(forceLoad)

            _articlesState.emit(ArticleState.Success(articles = fetchedArticles))
        }
    }
}