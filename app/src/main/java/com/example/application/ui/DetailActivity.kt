package com.example.application.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.application.R
import com.example.application.data.Articles
import com.example.application.data.News
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val news = intent.getSerializableExtra("news")
        val position = intent.getIntExtra("index", 0)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "News"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var items = mutableListOf<Articles>()
        items.add((news as News).articles.get(position))
        items.addAll(news.articles)
        val adapter =
            NewsAdapter(this, items, { position ->
                startDetailActivity(news, position - 1)
            }, { item ->
                viewModel.addNewsToDB(this, item)
            })
        recycler_view.adapter = adapter
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val news = intent?.getSerializableExtra("news")
        val position = intent?.getIntExtra("index", 0)
        var items = mutableListOf<Articles>()
        items.add((news as News).articles.get(position ?: 0))
        items.addAll(news.articles)
        val adapter =
            NewsAdapter(this, items, { position ->
                startDetailActivity(news, position - 1)
            }, { item ->
                viewModel.addNewsToDB(this, item)
            })
        recycler_view.adapter = adapter
    }

    private fun startDetailActivity(news: News, index: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("news", news)
        intent.putExtra("index", index)
        startActivity(intent)
    }
}