package com.xxyl.multimediatest9

/**
 * author: zhang
 * created on: 2020/12/25 17:18
 * description: 可将 obj.func(arg) 转换为 obj func arg 的形式
 * 1.infix 函数不能定义成顶层函数的，它必须是某个类的成员函数，可以使用扩展函数的方式将它定义到某个类当中
 * 2.infix 函数必须接收且只能接收一个参数，至于参数类型是没有限制的。
 */

fun main() {
    if ("hello world".startsWith("hello")){
        println(true)
    }

    if ("hello world" beginWith "hello") println(true)
}
// content beginWith con
infix fun String.beginWith(prefix: String): Boolean = startsWith(prefix)
//list has element
infix fun <T> Collection<T>.has(element: T): Boolean = contains(element)
// a to b
infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)