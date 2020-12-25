package com.xxyl.fragmenttest.bestpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.fragmenttest.R
import com.xxyl.fragmenttest.bestpractice.fragment.NewsContentFragment
import com.xxyl.fragmenttest.bestpractice.news.News
import kotlinx.android.synthetic.main.activity_news_content.*

class NewsContentActivity : AppCompatActivity() {

    companion object{
        fun start(activity: Activity, news: News){
            val intent = Intent(activity, NewsContentActivity::class.java).apply {
                putExtra("news_title", news.title)
                putExtra("news_content", news.content)
            }

            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        val title = intent.getStringExtra("news_title")
        val content = intent.getStringExtra("news_content")
        if (title != null && content != null){
            val fragment = fragNewsContent as NewsContentFragment
            fragment.refresh(title, content)//刷新newscontentfragment页面
        }
    }
}