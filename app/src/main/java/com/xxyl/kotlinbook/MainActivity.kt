package com.xxyl.kotlinbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxyl.kotlinbook.Adapter.ForecastListAdapter
import com.xxyl.kotlinbook.Request.Request
import com.xxyl.kotlinbook.domian.RequestForecastCommand
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    private val items = listOf<String>(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7"
    )
    val KEY = "b38b7c040cea4f1e8362657efdbabba6"
    var LOCATION = "101010100"

    val url = "https://devapi.heweather.net/v7/weather/3d?location=$LOCATION&key=$KEY"

    private lateinit var mGetWeatherBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //使用Anko库实例化控件
        val forecastList = find<RecyclerView>(R.id.main_forecast_list)
//        val forecastList = findViewById<RecyclerView>(R.id.main_forecast_list) as RecyclerView
        //set LayoutManager(Linear)
        forecastList.layoutManager = LinearLayoutManager(this)
        //add　Adapter
//        forecastList.adapter = ForecastListAdapter(items)
        mGetWeatherBtn = find(R.id.main_btn)
        mGetWeatherBtn.setOnClickListener {
            doAsync {
//                Request(url).run()
                val result = RequestForecastCommand("101010100").execute()
                uiThread {
//                    longToast("Request performed")
                    forecastList.adapter = ForecastListAdapter(result)
                }
            }
        }

        //扩展函数可以被声明在任何文件中，因此有个通用的实践是把一系列有关的函数放在一个新建的文件里。
        //使用Anko中的扩展函数
        longToast("Hello world !!!")
    }


    fun add(x: Int, y: Int) : Int{
        return x + y
    }
    //如果返回的结果可以使用一个表达式计算出来,可以用等号代替大括号
    fun add1(x: Int, y: Int): Int = x + y

    /**
     * 给参数添加默认值
     */
    fun toast(message: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, message, length).show()
    }

    /**
     * 三参方法,可使用参数名指定使用的参数
     */
    fun niceToast(message: String,
                  tag: String = classLoader.javaClass.simpleName,
                  length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,"[$tag] $message",length).show()
    }

    fun test(){
        //使用参数名指定调用的参数
        niceToast(message = "hello", length = Toast.LENGTH_SHORT)
    }
}