package com.xxyl.fragmenttest.bestpractice

/**
 * author: zhang
 * created on: 2020/12/23 10:08
 * description:运算符重载
 */
fun main() {
    val money1 = Money(20)
    val money2 = Money(30)
    val money = money1 + money2
    println("Count : ${money.value} ￥") //Count : 50 ￥
}

class Money(val value: Int){
    /**
     * +运算符指定重载函数
     * @param money Money
     * @return Money
     */
    operator fun plus(money: Money): Money {
        return Money(value + money.value)
    }

    /**
     * 运算符重载
     * @param money Int
     * @return Money
     */
    operator fun plus(money: Int): Money{
        return Money(value + money)
    }


}

