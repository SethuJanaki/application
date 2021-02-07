package com.example.application.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.application.data.Articles
import java.util.*

@Entity(tableName = "News")
data class NewsItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?,
    @ColumnInfo(name = "content")
    val content: String?,
    @ColumnInfo(name = "source")
    val source: String?,
) {

    companion object {
        fun getNewsItem(item: Articles): NewsItem {
            return NewsItem(
                title = item.title,
                urlToImage = item.urlToImage,
                content = item.content,
                source = item.source.name
            )
        }
    }
}
