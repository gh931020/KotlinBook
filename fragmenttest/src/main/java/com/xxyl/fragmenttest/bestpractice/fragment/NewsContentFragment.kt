package com.xxyl.fragmenttest.bestpractice.fragment

import android.icu.text.CaseMap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xxyl.fragmenttest.R
import kotlinx.android.synthetic.main.fragment_news_content.*

/**
 * author: zhang
 * created on: 2020/12/22 16:52
 * description:
 */
class NewsContentFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_content, container, false)
    }

    fun refresh(title: String, content: String){
        llContentLayout.visibility = View.VISIBLE
        tvNewsTitle.text = title // 设置新闻标题
        tvNewsContent.text = content //设置新闻内容
    }
}