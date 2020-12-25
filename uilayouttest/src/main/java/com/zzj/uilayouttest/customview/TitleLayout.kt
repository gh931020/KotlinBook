package com.zzj.uilayouttest.customview

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.zzj.uilayouttest.R
import kotlinx.android.synthetic.main.title.view.*

/**
 * author: zhang
 * created on: 2020/12/21 17:01
 * description:
 */
class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title,this)
        titleBack.setOnClickListener {
            (context as Activity).finish()
        }
        titleEdit.setOnClickListener {
            Toast.makeText(context, "you clicked edit button",Toast.LENGTH_SHORT).show()
        }
    }
}