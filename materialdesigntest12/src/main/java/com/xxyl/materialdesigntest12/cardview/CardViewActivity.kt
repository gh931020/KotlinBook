package com.xxyl.materialdesigntest12.cardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.xxyl.materialdesigntest12.R
import kotlinx.android.synthetic.main.activity_card_view.*
import kotlin.concurrent.thread

class CardViewActivity : AppCompatActivity() {
    private val dataList = ArrayList<Fruit>()
    lateinit var adapter: FruitAdapter
    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple), Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange), Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear), Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry), Fruit("Mango", R.drawable.mango)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)
        initFruits()
        adapter = FruitAdapter(this, dataList)
        val manager = GridLayoutManager(this, 2)
        rvFruit.layoutManager = manager
        rvFruit.adapter = adapter
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun initFruits() {
        dataList.clear()
        repeat(50){
            val index = (0 until fruits.size).random()
            dataList.add(fruits[index])
        }
    }
}