package com.example.application.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.application.data.Articles
import com.example.application.data.News
import com.example.application.database.NewsItem
import com.example.application.database.NewsRepository
import com.example.application.database.SearchItem

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class NewsViewModel() : ViewModel() {

    private val _newsLiveData = MutableLiveData<News>()
    val newsLiveData: LiveData<News>
        get() = _newsLiveData

    private val _newsItemLiveData = MutableLiveData<List<NewsItem>>()
    val newsItemLiveData: LiveData<List<NewsItem>>
        get() = _newsItemLiveData

    private val _textLiveData = MutableLiveData<List<SearchItem>>()
    val textLiveData: LiveData<List<SearchItem>>
        get() = _textLiveData

    private val compositeDisposable = CompositeDisposable()


    fun getNews() {
        NewsRepository().getNews()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _newsLiveData.postValue(it)
            }, {
            })
            .addTo(compositeDisposable)
    }

    fun getNews(text:String) {
        NewsRepository().getNews(text)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _newsLiveData.postValue(it)
            }, {
            })
            .addTo(compositeDisposable)
    }


    fun addNewsToDB(context: Context, item: Articles) {
        NewsRepository()
            .addNewsToDB(context, item)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {
            })
            .addTo(compositeDisposable)
    }

    fun getNewsBookmark(context: Context) {
        NewsRepository().getNews(context)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _newsItemLiveData.postValue(it)
            }, {
                it
            })
            .addTo(compositeDisposable)
    }

    fun addSearchToDB(context: Context, item: String) {
        NewsRepository()
            .addSearchToDB(context, item)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
            }, {
            })
            .addTo(compositeDisposable)
    }

    fun getSearch(context: Context) {
        NewsRepository().getSearch(context)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _textLiveData.postValue(it)
            }, {
                it
            })
            .addTo(compositeDisposable)
    }
}
