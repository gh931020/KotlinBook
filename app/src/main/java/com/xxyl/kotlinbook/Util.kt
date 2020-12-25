package com.xxyl.kotlinbook

/**
 * author: zhang
 * created on: 2020/12/21 11:24
 * description:
 */
class Util {
    fun action1(){
        println("I'm action1")
    }

    companion object{
        fun action2(){
            println("I'm action2, a companion object's function")
        }
    }
}