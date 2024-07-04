package com.tahadev.dailypulse.articles.data.remote.dto

import com.tahadev.dailypulse.articles.domain.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("publishedAt")
    val publishedAt: String,
    @SerialName("author")
    val author: String? = null,
    @SerialName("urlToImage")
    val urlToImage: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("source")
    val source: Source,
    @SerialName("title")
    val title: String,
    @SerialName("url")
    val url: String,
    @SerialName("content")
    val content: String? = null
)

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        description = description ?: "Click to find out more",
        date = publishedAt,
        imageUrl = urlToImage ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpeg?v=1698758587&w=1920&h=1080"
    )
}
