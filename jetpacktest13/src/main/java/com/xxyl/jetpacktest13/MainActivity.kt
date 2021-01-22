package com.xxyl.jetpacktest13

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.xxyl.jetpacktest13.lifecycle.LifeCycleActivity
import com.xxyl.jetpacktest13.livedata.LiveDataActivity
import com.xxyl.jetpacktest13.viewmodel.ViewModelActivity
import com.xxyl.jetpacktest13.workmanager.WorkManagerActivity
import kotlinx.android.synthetic.main.activity_live_data.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnViewModel.setOnClickListener {
            start<ViewModelActivity>(this){}
        }
        btnLifeCycles.setOnClickListener {
            start<LifeCycleActivity>(this){}
        }
        btnLiveData.setOnClickListener {
            start<LiveDataActivity>(this){}
        }
        btnWorkManager.setOnClickListener {
            start<WorkManagerActivity>(this){}
        }
    }
}

inline fun <reified T> start(context: Context,block: Intent.() -> Unit){
    val  intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}
fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context, this, duration).show()
}
