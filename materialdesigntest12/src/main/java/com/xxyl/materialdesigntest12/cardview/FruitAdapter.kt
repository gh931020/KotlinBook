package com.xxyl.materialdesigntest12.cardview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xxyl.materialdesigntest12.R
import com.xxyl.materialdesigntest12.start
import kotlinx.android.synthetic.main.activity_main.view.*
import org.w3c.dom.Text

/**
 * author: zhang
 * created on: 2021/1/4 13:43
 * description:
 */
class FruitAdapter(val context: Context, val dataList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.tvFruit)
        val image = view.findViewById<ImageView>(R.id.ivFruit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fruit, parent, false))
        holder.itemView.setOnClickListener {
            val fruit = dataList[holder.adapterPosition]
            start<FruitActivity>(context){
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageIdRes)
            }
        }
        return holder
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var fruit = dataList[position]
        holder.name.text = fruit.name
        Glide.with(context).load(fruit.imageIdRes).into(holder.image)

    }


}