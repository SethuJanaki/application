package com.example.application.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.application.R
import com.example.application.data.News
import kotlinx.android.synthetic.main.activity_book_mark.*

class BookMarkActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_mark)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Bookmark News"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getNewsBookmark(this)
        viewModel.newsItemLiveData.observe(this, Observer { news ->
            val adapter =
                BookMarkAdapter(news, { position ->
                    // startDetailActivity(news, position)
                })
            recycler_view.adapter = adapter
        })
    }

    private fun startDetailActivity(news: News, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("news", news)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}