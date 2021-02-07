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

package com.android.example.github.api

import com.example.application.data.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET("/v2/top-headlines?country=us&category=business&apiKey=4859e35e6f464577a7ef07bbc6bb1836")
    fun getNewsHeadlines(): Observable<News>

    @GET("/v2/top-headlines?apiKey=4859e35e6f464577a7ef07bbc6bb1836")
    fun getNewsHeadlines(@Query("q") query: String): Observable<News>

}
