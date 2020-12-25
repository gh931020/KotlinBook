package com.xxyl.broadcasttest

/**
 * author: zhang
 * created on: 2020/12/23 15:26
 * description:高阶函数
 */
// 高阶函数类型基本规则  (String, Int) -> Unit
// ->左边代表函数参数，多个参数之间用， 隔开，无参数直接使用 ()->
// ->右边代表返回值类型，若无返回值，使用Unit
fun example(func:(String, Int)->Unit){
    func("hello", 123)
}

fun myFunc(string: String, value: Int){
    println("$string, $value")
}

fun num1AndNum2(num1: Int, num2: Int, operation:(Int, Int)->Int): Int{
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int): Int{
    return num1 + num2
}
fun minus(num1: Int, num2: Int): Int{
    return num1 - num2
}
fun main() {
    example(::myFunc)
    val num1 = 50
    val num2 = 25
    val result1 = num1AndNum2(num1, num2, ::plus)
    //可以使用lamda表达式代替函数,与minus函数效果相同
    val result2 = num1AndNum2(num1, num2){n1,n2->
        n1 - n2
    }
}
//inline 内联函数  将调用的函数表达式逻辑复制到调用处，避免高阶函数每次调用都会创建一个Function匿名对象，
// 减少内存开销
//lambda表达式不允许直接使用return关键字，可使用return@funcName的方式进行局部返回。但如果将高阶函数
// 声明为内联函数，则可以使用return，代表直接返回当前方法。因为内联函数相当于将代码复制到调用处，不再属于lambda表达式
//如果我们在高阶函数中创建了另外的lambda或者匿名类的实现，并且这些实现中调用函数类型参数，此时再将高阶函数声明成内联函数，
//就一定会提示错误。可使用crossline解决
inline fun runRunnable(crossinline block:()->Unit){
    val runnable = Runnable {
        block()
    }
    runnable.run()
}