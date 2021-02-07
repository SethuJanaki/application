package com.example.application.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.database.SearchItem
import kotlinx.android.synthetic.main.search_item.view.news_headline

class SearchAdapter(
    val items: List<SearchItem>,
    val clicklistener: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.search_item,
            parent, false
        )
        return SearchViewHolder(view)
    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind( items[position], position, clicklistener)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            item: SearchItem, position: Int,
            clicklistener: ((Int) -> Unit)?) =
            with(itemView) {
                news_headline.text = item.title
                setOnClickListener {
                    clicklistener?.invoke(position)
                }
            }
    }
}
