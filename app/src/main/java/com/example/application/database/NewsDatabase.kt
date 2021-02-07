package com.example.application.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [NewsItem::class, SearchItem::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newDao(): NewsDao

    companion object {

        private var INSTANCE: NewsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NewsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java, "Sample.db"
            ).build()

    }
}
