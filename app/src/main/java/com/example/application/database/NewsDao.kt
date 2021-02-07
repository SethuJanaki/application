package com.example.application.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

import io.reactivex.Flowable

@Dao
interface NewsDao {

    @Query("SELECT * FROM News")
    fun getNews(): Flowable<List<NewsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsItem): Completable

    @Query("SELECT * FROM Search")
    fun getSearch(): Flowable<List<SearchItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(text: SearchItem): Completable

}
