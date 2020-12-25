package com.zzj.uilayouttest.listview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zzj.uilayouttest.bean.Fruit
import com.zzj.uilayouttest.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    companion object {
        fun start(context: Activity) {
            context.startActivity(Intent(context, ListViewActivity::class.java))
        }
    }

//    private val listData = listOf<String>(
//        "Apple", "Banner", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry",
//        "Cherry", "Mango",
//        "Apple", "Banner", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry",
//        "Cherry", "Mango"
//    )
    private val fruitList= ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        initFruit()
        val adapter = FruitAdapter(
            this,
            R.layout.item_fruit,
            fruitList
        )
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name,Toast.LENGTH_SHORT).show()

        }
    }

    private fun initFruit() {
        //把lambda中的语句执行 n times
        repeat(2){
            fruitList.add(
                Fruit(
                    "Apple",
                    R.drawable.apple
                )
            )
            fruitList.add(
                Fruit(
                    "Banner",
                    R.drawable.banana
                )
            )
            fruitList.add(
                Fruit(
                    "Orange",
                    R.drawable.orange
                )
            )
            fruitList.add(
                Fruit(
                    "Watermelon",
                    R.drawable.watermelon
                )
            )
            fruitList.add(
                Fruit(
                    "Pear",
                    R.drawable.pear
                )
            )
            fruitList.add(
                Fruit(
                    "Grape",
                    R.drawable.grape
                )
            )
            fruitList.add(
                Fruit(
                    "Pineapple",
                    R.drawable.pineapple
                )
            )
            fruitList.add(
                Fruit(
                    "Strawberry",
                    R.drawable.strawberry
                )
            )
            fruitList.add(
                Fruit(
                    "Cherry",
                    R.drawable.cherry
                )
            )
            fruitList.add(
                Fruit(
                    "Mango",
                    R.drawable.mango
                )
            )
        }
    }
}