package com.zzj.uilayouttest.listview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.zzj.uilayouttest.bean.Fruit
import com.zzj.uilayouttest.R

/**
 * author: zhang
 * created on: 2020/12/21 17:45
 * description:
 */
class FruitAdapter(activity: Activity,val resourceId: Int, val data: List<Fruit>): ArrayAdapter<Fruit>(activity, resourceId, data) {
    override fun getCount(): Int {
        return data.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId, parent,false)
            val fruitImage = view.findViewById<ImageView>(R.id.fruitImage)
            val fruitName = view.findViewById<TextView>(R.id.fruitName)
            viewHolder = ViewHolder(fruitImage, fruitName)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val fruit = data[position]
        fruit.apply {
            viewHolder.fruitImage.setImageResource(imageId)
            viewHolder.fruitName.text = name
        }
        return view
    }
    //使用viewholder缓存视图，达到优化listview显示的效果
    inner class ViewHolder(val fruitImage: ImageView,val fruitName: TextView)

}