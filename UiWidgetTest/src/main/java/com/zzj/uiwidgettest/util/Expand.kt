package com.zzj.uiwidgettest.util

import android.widget.Toast
import com.zzj.uiwidgettest.UiApplication

/**
 * author: zhang
 * created on: 2020/12/21 13:58
 * description:
 */
fun CharSequence.toast(duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(UiApplication.application, this.toString(), duration).show()
}