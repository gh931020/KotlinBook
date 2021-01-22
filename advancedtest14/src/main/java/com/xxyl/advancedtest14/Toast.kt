package com.xxyl.advancedtest14

import android.app.Application
import android.widget.Toast

/**
 * author: zhang
 * created on: 2021/1/5 15:26
 * description:
 */
//此处运用全局的context避免了每次调用都要传入context,也保证了context的统一
fun String.showToast(duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(MyApplication.context, this, duration).show()
}
