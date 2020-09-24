package com.xxyl.kotlinbook.bean

import java.util.*

/**
 * author: zhang
 * created on: 2020/9/21 10:41
 * description:
 */
data class Forecast(val date: Date, val temperature: Float, val details: String)
/*
数据类可避免java中重复的POJO模板代码
通过数据类,可以方便的得到很多有趣的函数:
- equals(): 它可以比较两个对象的属性来确保他们是相同的
- hashCode(): 从属性中计算出来
- copy(): 可以拷贝一个对象,根据需要修改里面的属性
    val f1 = Forecast(Date(), 27.5f, "Shiny day")
    val f2 = f1.copy(temperature = 30f)
- 可以映射对象到变量中的函数
    val f1 = Forecast(Date(), 27.5f, "Shiny day")
    val (date, temperature, details) = f1
    映射对象的每一个属性到一个变量中,多声明
    上面这个多声明会被编译成下面的代码:
    val date = f1.component1()
    val temperature = f1.component2()
    val details = f1.component3()
    这个特性可在很多情况下帮助我们简化代码,比如Map类中:
    for((key, value) in map ){
        Log.d("map","key: $key, value:$value")
    }
 */