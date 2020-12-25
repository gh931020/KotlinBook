package com.zzj.uilayouttest.recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.zzj.uilayouttest.R
import com.zzj.uilayouttest.bean.Fruit
import kotlinx.android.synthetic.main.activity_recycler.view.*
import kotlinx.android.synthetic.main.item_fruit.view.*

/**
 * author: zhang
 * created on: 2020/12/22 10:49
 * description:
 */
class FruitAdapter(val activity: Activity, val data: List<Fruit>): RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val fruitImage = view.findViewById<ImageView>(R.id.fruitImage)
        val fruitName = view.findViewById<TextView>(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruit, parent, false)
        val viewHolder =ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val fruit = data[viewHolder.absoluteAdapterPosition]
            Toast.makeText(parent.context, "you clicked item view ${fruit.name}",Toast.LENGTH_SHORT).show()
        }
        viewHolder.fruitImage.setOnClickListener {
            val fruit = data[viewHolder.absoluteAdapterPosition]
            Toast.makeText(parent.context, "you clicked imageView ${fruit.name}",Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = data[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

}