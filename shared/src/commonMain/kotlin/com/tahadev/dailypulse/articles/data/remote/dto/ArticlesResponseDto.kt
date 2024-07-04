package com.tahadev.dailypulse.articles.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponseDto(
	@SerialName("totalResults")
	val totalResults: Int = 0,
	@SerialName("articles")
	val articles: List<ArticleDto> = emptyList(),
	@SerialName("status")
	val status: String? = null
)
