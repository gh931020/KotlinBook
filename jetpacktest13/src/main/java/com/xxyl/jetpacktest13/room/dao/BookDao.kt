package com.xxyl.jetpacktest13.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xxyl.jetpacktest13.room.entity.Book

/**
 * author: zhang
 * created on: 2021/1/5 14:02
 * description:
 */
@Dao
interface BookDao {
    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from book")
    fun loadAllBooks(): List<Book>
}