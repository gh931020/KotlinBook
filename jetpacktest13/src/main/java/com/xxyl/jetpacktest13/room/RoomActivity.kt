package com.xxyl.jetpacktest13.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xxyl.jetpacktest13.R
import com.xxyl.jetpacktest13.room.database.AppDatabase
import com.xxyl.jetpacktest13.room.entity.User
import kotlinx.android.synthetic.main.activity_room.*
import kotlin.concurrent.thread

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        //获取操作dao
        val dao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("zhang", "san", 15)
        val user2 = User("li", "si", 14)
        //增
        btnInsert.setOnClickListener {
            thread {
                user1.id = dao.insertUser(user1)
                user2.id = dao.insertUser(user2)
            }
        }
        //删
        btnDelete.setOnClickListener {
            thread {
//                dao.deleteUser(user1)
                dao.deleteUserByLaseName("zhang")
            }
        }
        //改
        btnUpdate.setOnClickListener {
            thread {
                user1.age = 25
                dao.updateUser(user1)
            }
        }
        //查
        btnQuery.setOnClickListener {
            thread {
                dao.loadAllUsers().forEach(){user->
                    Log.d("Room", user.firstName)
                }
            }
        }

    }
}