package com.example.application.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.application.R
import com.example.application.data.News
import kotlinx.android.synthetic.main.activity_book_mark.recycler_view
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Search News"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        searchText.setOnEditorActionListener{ view, keyCode, event ->
            if (keyCode == EditorInfo.IME_ACTION_DONE) {
                viewModel.addSearchToDB(this, searchText.text.toString())
                viewModel.getNews(searchText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }

        viewModel.getSearch(this)
        viewModel.textLiveData.observe(this, Observer { items ->
            val adapter =
                SearchAdapter(items, { item ->
                    searchText.setText(items.get(item).title)
                })
            recycler_view.adapter = adapter
        })
        viewModel.newsLiveData.observe(this, Observer { items ->
            startDetailActivity(items, 0)
        })
    }

    private fun startDetailActivity(news: News, index: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("news", news)
        intent.putExtra("index", index)
        startActivity(intent)
    }
}