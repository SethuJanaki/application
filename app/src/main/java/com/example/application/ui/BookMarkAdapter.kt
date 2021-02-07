package com.example.application.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.application.R
import com.example.application.database.NewsItem
import kotlinx.android.synthetic.main.news_item.view.*

class BookMarkAdapter(
    val items: List<NewsItem>,
    val clicklistener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<BookMarkAdapter.BookMarkNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkNewsViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.news_item,
            parent, false
        )
        return BookMarkNewsViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: BookMarkNewsViewHolder, position: Int) {
        holder.bind(items[position], position, clicklistener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class BookMarkNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: NewsItem, position: Int,
            clicklistener: ((Int) -> Unit)?) =
            with(itemView) {
                Glide.with(itemView.context).load(item.urlToImage).into(news_image)
                news_headline.text = item.title
                news_content.text = item.content
                news_content_text.text = item.source
                news_bookmark.setImageResource(R.drawable.bookmarkselected)
                news_container.setOnClickListener {
                    clicklistener?.invoke(position)
                }
            }
    }


}