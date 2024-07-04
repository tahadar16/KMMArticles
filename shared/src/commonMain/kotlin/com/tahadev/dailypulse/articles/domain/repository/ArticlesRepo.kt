package com.tahadev.dailypulse.articles.domain.repository

import com.tahadev.dailypulse.articles.domain.model.Article

interface ArticlesRepo {
    suspend fun fetchArticles(country: String, category: String, forceLoad: Boolean): List<Article>
}