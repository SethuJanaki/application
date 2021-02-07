package com.example.application.data

import java.io.Serializable


data class News(
    val id:Int?,
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
):Serializable

data class Articles(
    val id: Int,
    val author: String,
    val title: String,
    val urlToImage: String,
    val description: String,
    val url: String,
    val content: String,
    val publishedAt: String,
    val source: Source,
    var selected: Boolean = false
) : Serializable

data class Source(
    val id: String?,
    val name: String
) : Serializable
