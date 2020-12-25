package com.xxyl.contentprovidertest8

import androidx.appcompat.app.ActionBarDrawerToggle
import kotlin.reflect.KProperty

/**
 * author: zhang
 * created on: 2020/12/25 10:30
 * description:委托
 * 1.类委托
 *  通过类委托可直接将委托类的方法属性赋予MySet，减少模板代码，且可自由创建属于自己的方法，
 *  类似于将一个类吸收后拥有它的技能，还可修炼自己的独特技能
 */
class MySet<T>(val helperSet: HashSet<T>): Set<T> by helperSet{
    fun helloWorld(){
        println("hello world!")
    }

    override fun isEmpty(): Boolean {
        return false
    }
}
/**
 *2.委托属性
 *  将一个属性（字段）的具体实现，委托给另一个类去完成
 */
class MyClass{
    var p by Delegate()

}
class Delegate {
    var propValue: Any?= null
    operator fun getValue(myClass: MyClass, prop:KProperty<*>): Any?{
        return propValue
    }
    //当propValue为val时，不需要setValue方法
    operator fun setValue(myClass: MyClass, property: KProperty<*>, value: Any?) {
        propValue = value
    }

}
/**
 * 3.懒加载 lazy
 * val p by lazy{ }
 * 在lazy函数中会创建并返回一个Delegate对象，当我们调用p属性的时候，其实调用的时Delegate对象的getValue方法，
 * 然后getValue方法中又会调用lazy函数传入的lambda表达式，这样表达式中的代码就可以得到执行，并调用p属性后得到
 * 的值就是lambda表达式最后一行代码的返回值
 *  基本原理：
 */
class Later<T>(val block:()->T){
    var value: Any?= null
    operator fun getValue(any: Any?, prop: KProperty<*>): T{
        //对要生成的属性进行缓存，保证不会多次创建对象
        if (value == null) value = block()
        return value as T
    }
}
//顶层函数，可以直接通过later进行调用
fun <T>later(block:()->T) = Later(block)
