package com.zzj.uilayouttest.recyclerview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzj.uilayouttest.R
import com.zzj.uilayouttest.bean.Fruit
import com.zzj.uilayouttest.listview.ListViewActivity
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    companion object {
        fun start(context: Activity) {
            context.startActivity(Intent(context, RecyclerActivity::class.java))
        }
    }
    private val fruitList= ArrayList<Fruit>()
    lateinit var adapter: FruitAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        initFruit()
        adapter = FruitAdapter(this, fruitList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
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