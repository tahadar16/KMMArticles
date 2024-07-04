package com.tahadev.dailypulse.articles.data.local

import com.tahadev.dailypulse.articles.data.remote.dto.ArticleDto
import com.tahadev.dailypulse.articles.domain.model.Article
import com.tahadev.dailypulse.db.DailyPulseDatabase

class ArticleDataSource(private val database: DailyPulseDatabase) {
    fun fetchArticles(): List<Article> {
        return database.dailyPulseDatabaseQueries.getAllArticles(::mapToArticle).executeAsList()
    }

    fun insertArticles(list: List<Article>) {
        database.dailyPulseDatabaseQueries.transaction {
            list.forEach { article ->
                insertArticle(article)
            }
        }
    }

    fun removeArticles() {
        database.dailyPulseDatabaseQueries.removeAllArticles()
    }

    private fun insertArticle(article: Article) {
        database.dailyPulseDatabaseQueries.insertArticle(
            article.title,
            article.description,
            article.date,
            article.imageUrl
        )
    }

    private fun mapToArticle(
        title: String,
        description: String,
        date: String,
        imageUrl: String
    ): Article {
        return Article(
            title,
            description,
            date,
            imageUrl
        )
    }
}