package com.xxyl.jetpacktest13.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.xxyl.jetpacktest13.R
import kotlinx.android.synthetic.main.activity_view_model.*

class ViewModelActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    lateinit var viewModel: MyViewModel
    lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_view_model)
        sp = getPreferences(Context.MODE_PRIVATE)
        //获取sp中存储的值
        val countReserved = sp.getInt("count_reserved", 0)
        //获取viewModel.不可以直接去创建ViewModel实例,因为其有独立的生命周期,且要长于Activity.如果我们
        // 在onCreate中创建VIewmodel实例,那么每次OnCreate方法执行的时候都会创建一个新的实例,这样当手机
        // 屏幕发生反转的时候,就无法保证其中的数据了
//        viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel = ViewModelProviders.of(this, MyViewModelFactory(countReserved)).get(MyViewModel::class.java)

        btnAdd.setOnClickListener {
            viewModel.counter ++
            refreshCounter()
        }
        btnClear.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
        refreshCounter()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }
    private fun refreshCounter() {
        tvCounter.text = viewModel.counter.toString()
    }
    
}