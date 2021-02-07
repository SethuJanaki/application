/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.application.database

import android.content.Context
import com.android.example.github.api.NewsService
import com.example.application.data.Articles
import com.example.application.data.News
import com.example.application.data.RetrofitBuilder
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable


class NewsRepository() {

    fun getNews(): Observable<News> {
        return RetrofitBuilder
            .buildService(NewsService::class.java).getNewsHeadlines()
    }

    fun getNews(text: String): Observable<News> {
        return RetrofitBuilder
            .buildService(NewsService::class.java).getNewsHeadlines(text)
    }

    fun addNewsToDB(context: Context, item: Articles): Completable {
        return NewsDatabase.getInstance(context).newDao()
            .insertNews(NewsItem.getNewsItem(item))
    }

    fun getNews(context: Context): Flowable<List<NewsItem>> {
        return NewsDatabase.getInstance(context).newDao()
            .getNews()
    }

    fun addSearchToDB(context: Context, item: String): Completable {
        return NewsDatabase.getInstance(context).newDao()
            .insertSearch(SearchItem.getSearchItem(item))
    }

    fun getSearch(context: Context): Flowable<List<SearchItem>> {
        return NewsDatabase.getInstance(context).newDao()
            .getSearch()
    }
}
