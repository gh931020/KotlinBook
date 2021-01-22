package com.xxyl.jetpacktest13.room.dao

import android.graphics.LinearGradient
import android.icu.text.UnicodeSet
import androidx.room.*
import com.xxyl.jetpacktest13.room.entity.User

/**
 * author: zhang
 * created on: 2021/1/5 11:16
 * description:
 */
@Dao
interface UserDao {
    /**
     * 插入用户
     * @param user User
     * @return Long
     */
    @Insert
    fun insertUser(user: User): Long

    /**
     * 更新用户
     * @param newUser User
     * @return Unit
     */
    @Update
    fun updateUser(newUser: User)

    /**
     * 查询所有用户
     * @return List<User>
     */
    @Query("select * from User")
    fun loadAllUsers(): List<User>

    /**
     * 查询指定年龄以上的用户
     * @param age Int
     * @return List<User>
     */
    @Query("select * from user where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    /**
     * 删除指定用户
     * @param user User
     * @return Unit
     */
    @Delete
    fun deleteUser(user: User)

    /**
     * 根据用户名删除用户
     * @param lastName String
     * @return Int
     */
    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLaseName(lastName: String): Int
}