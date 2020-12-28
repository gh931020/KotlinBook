package com.xxyl.servicetest10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.servicetest10.async.AsyncActivity
import com.xxyl.servicetest10.service.ServiceActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAsync.setOnClickListener {
            AsyncActivity.start(this)
        }
        btnService.setOnClickListener {
//            ServiceActivity.start(this)
            //利用内联函数的泛型实化以及高阶函数启动activity
            startActivity<ServiceActivity>(this){
//                putExtra("name", "name")
            }
        }
    }
}
