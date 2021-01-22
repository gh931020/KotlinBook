package com.xxyl.jetpacktest13.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author: zhang
 * created on: 2021/1/5 11:12
 * description:
 */
//将User声明为实体类
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {
    //添加id字段,使用注解将其设置为主键,自动生成设置为true
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}