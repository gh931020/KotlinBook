package com.xxyl.networktest11

import kotlinx.coroutines.*

/**
 * author: zhang
 * created on: 2020/12/30 16:56
 * description:协程
 */

fun main() {
    GlobalScope.launch {
        //单单这一句话不会输出，因为GlobalScope.launch创建的是一个顶层协程，
        // 这种协程在应用程序结束时也会跟着一起结束
        println("codes run in coroutine scope!")
        //可以让当前协程延迟指定时间后再运行，但它和Thread.sleep()方法不同，
        // delay函数是非阻塞式挂起函数，他只会挂起当前协程，并不会影响其它协程的运行。【只能再协程的作用域或其他挂起函数中使用】
        // Thread.sleep()方法会阻塞线程，这样运行在该线程下的所有协程都会被阻塞
        delay(1500)
        //这句不会被调用，因为主线程1s的时候已经结束，协程会被中断
        println("codes run in coroutine scope finished")
    }
    Thread.sleep(1000)
    //可以保证协程作用域内所有的代码和子协程没有全部执行完之前一直阻塞当前线程，
    //通常之应该再测试环境中使用，再正式环境中使用容易产生一些性能上的问题
    runBlocking {
        println("codes run in coroutine scope!")
        delay(1500)
        println("codes run in coroutine scope finished")
    }
}

/**
 * suspend 关键字，从语法层面上标记该函数为挂起函数，即可能是耗时操作
 * 除了标识功能外，无其它作用，不会挂起也不会切换线程，且无法提供协程作用域
 * suspend函数必须要协程作用域中调用
 * @return Unit
 */
suspend fun printDot(){
    println("hello world")
    delay(1000)
}

suspend fun printDot1() = coroutineScope {
    //coroutineScope可以允许使用launch创建子协程作用域,
    //可以保证作用域内的所有代码和子协程在全部执行完之前，一直阻塞当前线程，与runBlocking相同
    launch {
        println(".")
        delay(1000)
    }
}
//GlobalScope.launch和launch函数都会返回一个Job对象，只需要调用Job对象的cancel方法就可以取消
val job = GlobalScope.launch { //处理具体的逻辑
}
//job.cancel()
/**
 * 常用协程写法.优势在于所有经scope创建的协程，都可通过job1进行cancle（）
 */
val job1 = Job()
val scope = CoroutineScope(job)
//scope.launch{  }
//job.cancel()
/**
 * async函数，他会创建一个系的子协程并返回一个Deferred对象，如果我们想要获取代码块执行结果，只需调用deferred.await()
 * 再调用了async函数后，代码块中的代码会立即执行，当调用await()方法后如果代码块没有执行完，就会进行阻塞。
 * 因此，不同async{}.await之间组成了串联关系。为了不同子协程可以并行执行，可以统一在一处调用await
 */
fun async(){
    runBlocking {
        val result = async { 5+5 }.await()
        println(result)
    }
}
