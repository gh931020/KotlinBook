package com.xxyl.fragmenttest.bestpractice.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xxyl.fragmenttest.R
import com.xxyl.fragmenttest.bestpractice.NewsActivity
import com.xxyl.fragmenttest.bestpractice.NewsContentActivity
import com.xxyl.fragmenttest.bestpractice.fragment.NewsContentFragment
import com.xxyl.fragmenttest.bestpractice.news.News
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.android.synthetic.main.item_news_title.view.*

/**
 * author: zhang
 * created on: 2020/12/22 17:33
 * description:
 */
class NewsAdapter(private val activity: NewsActivity, private val news: List<News>, private val isTwoPanel: Boolean): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvTitle: TextView = view.findViewById(R.id.tvNewsTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_title, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val news = news[holder.absoluteAdapterPosition]
            if (isTwoPanel){
                //如果时双页模式，刷新newscontentfragment
                val fragment = activity.fragNewsContent as NewsContentFragment
                fragment.refresh(news.title, news.content)
            }else{
                //如果时单页模式，直接跳转至activity
                NewsContentActivity.start(activity, news)
            }
        }
        return holder
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = news[position].title
    }
}