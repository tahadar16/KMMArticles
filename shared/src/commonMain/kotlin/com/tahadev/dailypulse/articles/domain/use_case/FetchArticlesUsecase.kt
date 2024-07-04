package com.tahadev.dailypulse.articles.domain.use_case

import com.tahadev.dailypulse.articles.data.remote.dto.toArticle
import com.tahadev.dailypulse.articles.domain.model.Article
import com.tahadev.dailypulse.articles.domain.repository.ArticlesRepo
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class FetchArticlesUsecase(private val articlesRepo: ArticlesRepo) {

    suspend fun fetchArticles(forceLoad: Boolean): List<Article> {
        val articlesList = articlesRepo.fetchArticles("us", "business", forceLoad)
        return articlesList.map {
            it.copy(date = getDaysAgoString(it.date))
        }
    }

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}