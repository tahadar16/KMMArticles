package com.tahadev.dailypulse.articles.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Source(
	val name: String? = null,
	val id: String? = null
)
