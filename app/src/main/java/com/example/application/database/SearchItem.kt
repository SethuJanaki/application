package com.example.application.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.application.data.Articles
import java.util.*

@Entity(tableName = "Search")
data class SearchItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title")
    val title: String
) {
    companion object {
        fun getSearchItem(item: String): SearchItem {
            return SearchItem(
                title = item
            )
        }
    }
}