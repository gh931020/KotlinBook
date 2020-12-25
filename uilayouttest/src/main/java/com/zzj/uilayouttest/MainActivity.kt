package com.zzj.uilayouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zzj.uilayouttest.listview.ListViewActivity
import com.zzj.uilayouttest.recyclerview.RecyclerActivity
import com.zzj.uilayouttest.uibestpractice.UIBestPracticeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()//隐藏系统ActionBar，由于可以设置NoActionBar主题，因此要判空
        main_btn1.setOnClickListener {
            ListViewActivity.start(this)
        }
        main_btn2.setOnClickListener {
            RecyclerActivity.start(this)
        }
        main_btn3.setOnClickListener {
            UIBestPracticeActivity.start(this)
        }
    }
}
