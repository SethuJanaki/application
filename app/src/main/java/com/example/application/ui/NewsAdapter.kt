package com.example.application.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.ui.NewsAdapter.NewsViewHolder
import com.example.application.data.Articles
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(
    val context: Context,
    val items: List<Articles>,
    val clicklistener: ((Int) -> Unit)? = null,
    val bookmarklistener: ((Articles) -> Unit)? = null
) : RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.news_item,
            parent, false
        )
        if (viewType == 1) {
            view = LayoutInflater.from(parent.context).inflate(
                R.layout.top_news_item,
                parent, false
            )
        }
        return NewsViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) return 1 else return 2
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(context, items[position], position, clicklistener, bookmarklistener)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context,
            item: Articles, position: Int,
            clicklistener: ((Int) -> Unit)?, bookmarklistener: ((Articles) -> Unit)?
        ) =
            with(itemView) {
                if (position == 0 || position == 1) {
                    news_title.visibility = View.VISIBLE
                    news_line.visibility = View.VISIBLE
                } else {
                    news_title.visibility = View.GONE
                    news_line.visibility = View.GONE

                }
                if (context is DetailActivity && position == 0) {
                    news_content.text = item.content
                    news_title.text = item.title
                    news_line.visibility = View.GONE
                    news_headline.visibility = View.GONE
                }
                Glide.with(itemView.context).load(item.urlToImage).into(news_image)
                news_headline.text = item.title
                news_content.text = item.description
                news_content_text.text = item.source.name
                news_container.setOnClickListener {
                    clicklistener?.invoke(position)
                }
                if (item.selected) {
                    news_bookmark.setImageResource(R.drawable.bookmarkselected)
                } else {
                    news_bookmark.setImageResource(R.drawable.bookmark)
                }
                news_bookmark.setOnClickListener {
                    item.selected = !item.selected
                    if (item.selected) {
                        news_bookmark.setImageResource(R.drawable.bookmarkselected)
                    } else {
                        news_bookmark.setImageResource(R.drawable.bookmark)
                    }
                    bookmarklistener?.invoke(item)
                }
            }
    }


}