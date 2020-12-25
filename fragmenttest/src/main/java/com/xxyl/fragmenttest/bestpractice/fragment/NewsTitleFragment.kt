package com.xxyl.fragmenttest.bestpractice.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xxyl.fragmenttest.R
import com.xxyl.fragmenttest.bestpractice.NewsActivity
import com.xxyl.fragmenttest.bestpractice.adapter.NewsAdapter
import com.xxyl.fragmenttest.bestpractice.news.News
import com.xxyl.fragmenttest.bestpractice.times
import kotlinx.android.synthetic.main.fragment_news_title.*
import java.lang.StringBuilder

/**
 * author: zhang
 * created on: 2020/12/22 17:23
 * description:
 */
class NewsTitleFragment: Fragment() {

    private var isTwoPanel = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_title, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //通过判断activity中是否存在llContentlayout来决定显示fragment
        isTwoPanel = activity?.findViewById<View>(R.id.newsContentLayout) != null

        val layoutManager = LinearLayoutManager(activity)
        rvTitle.layoutManager = layoutManager
        val adapter = NewsAdapter(activity as NewsActivity, getNews(), isTwoPanel)
        rvTitle.adapter = adapter
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50){
            newsList.add(News("This is news title $i", getRandomLengthString("This is new content $i")))
        }
        return newsList
    }

    private fun getRandomLengthString(content: String): String =content * (1..20).random()
//    {
//        val n = (1..20).random()
//        val builder = StringBuilder()
//        repeat(n){
//            builder.append(content)
//        }
//        return builder.toString()
//    }
}