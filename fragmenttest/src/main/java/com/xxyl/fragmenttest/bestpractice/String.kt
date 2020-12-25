package com.xxyl.fragmenttest.bestpractice

import java.lang.StringBuilder

/**
 * author: zhang
 * created on: 2020/12/23 10:28
 * description:
 */
operator fun String.times(time: Int): String{
    val stringBuilder = StringBuilder()
    repeat(time){
        stringBuilder.append(this)
    }
    return stringBuilder.toString()
}