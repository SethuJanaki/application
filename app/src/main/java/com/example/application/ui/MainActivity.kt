package com.example.application.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.application.R
import com.example.application.data.News
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""
        supportActionBar?.setLogo(R.drawable.group);
        supportActionBar?.setDisplayUseLogoEnabled(true);

        viewModel.getNews()
        viewModel.newsLiveData.observe(this, Observer { news ->
            val adapter = NewsAdapter(
                this,
                news.articles,
                { position ->
                    startDetailActivity(news, position)
                },
                { item ->
                    viewModel.addNewsToDB(this, item)
                })
            recycler_view.adapter = adapter
        })

    }

    private fun startDetailActivity(news: News, index: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("news", news)
        intent.putExtra("index", index)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.news_bookmark -> {
                startActivity(Intent(this, BookMarkActivity::class.java))
            }
            R.id.search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            else -> {
            }
        }
        return true
    }
}