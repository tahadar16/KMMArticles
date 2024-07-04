package com.tahadev.dailypulse.articles.data.repository

import com.tahadev.dailypulse.articles.Constants.API_KEY
import com.tahadev.dailypulse.articles.data.local.ArticleDataSource
import com.tahadev.dailypulse.articles.data.remote.dto.ArticlesResponseDto
import com.tahadev.dailypulse.articles.data.remote.dto.toArticle
import com.tahadev.dailypulse.articles.domain.model.Article
import com.tahadev.dailypulse.articles.domain.repository.ArticlesRepo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesRepoImpl(
    private val httpClient: HttpClient,
    private val dataSource: ArticleDataSource
) : ArticlesRepo {

    override suspend fun fetchArticles(
        country: String,
        category: String,
        forceLoad: Boolean
    ): List<Article> {

        if (forceLoad) {
            dataSource.removeArticles()
            return fetchFromServer(country, category)
        }

        val articles = dataSource.fetchArticles()
        return articles.ifEmpty {
            fetchFromServer(country, category)
        }
    }

    private suspend fun fetchFromServer(country: String, category: String): List<Article> {
        val response: ArticlesResponseDto =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=${API_KEY}")
                .body()
        val filteredArticles = response.articles
            .filter { it.source.id != null }
            .map { it.toArticle() }
        dataSource.insertArticles(filteredArticles)
        return filteredArticles
    }
}