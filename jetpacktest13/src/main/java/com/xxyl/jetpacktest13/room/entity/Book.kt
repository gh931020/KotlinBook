package com.xxyl.jetpacktest13.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * author: zhang
 * created on: 2021/1/5 14:00
 * description:
 */
@Entity
data class Book(var name: String, var pages: Int, var author: String) {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0
}