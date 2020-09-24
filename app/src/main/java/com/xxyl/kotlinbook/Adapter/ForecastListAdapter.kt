package com.xxyl.kotlinbook.Adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xxyl.kotlinbook.domian.ForecastList

/**
 * author: zhang
 * created on: 2020/9/17 15:35
 * description:
 */
class ForecastListAdapter(val weekForecast: ForecastList):
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {


    class ViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TextView(parent.context))
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    /**
     * with接收一个对象和一个扩展函数作为它的参数,然后使这个对象扩展这个函数.
     * 这表示所有我们在括号中编写的代码都是作为对象(第一个参数)的一个扩展函数
     * 我们就可以像作为this一样使用所有它的public方法和属性
     * 当我们针对同一个对象做很多操作的时候这个非常有利于简化代码
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(weekForecast.dailyForecast[position]){
            holder.textView.text = "$date - $description - $high/$low"
        }
//        holder.textView.text = weekForecast[position]
    }
}