package com.xxyl.datasavetest7

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xxyl.datasavetest7.db.SQLiteHelper
import kotlinx.android.synthetic.main.activity_db.*
import java.lang.Exception
import java.lang.NullPointerException

class DbActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, DbActivity::class.java)
            context.startActivity(starter)
        }
    }

    private lateinit var helper: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)
        //数据库版本大于上一版本，执行update操作
        helper = SQLiteHelper(this, "BookStore.db", 2)
        btnDbCreate.setOnClickListener {
            //获取可写数据库，不存在则创建
            helper.writableDatabase
        }
        //插入数据
        btnInsert.setOnClickListener {
            val db = helper.writableDatabase
            val value1 = ContentValues().apply {
                put("name", " The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, value1)
            val value2 = ContentValues().apply {
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 555)
                put("price", 19.95)
            }
            db.insert("Book", null, value2)

//            db.execSQL("insert into Book(name, author, pages, price) values(?,?,?,?)", arrayOf("The Da Vinci Code", "Dan Brown", 454, 16.96))
//            db.execSQL("insert into Book(name, author, pages, price) values(?,?,?,?)", arrayOf("The Lost Symbol", "Dan Brown", 555, 19.96))
        }
        //更改数据
        btnUpdate.setOnClickListener {
            val db = helper.writableDatabase
            val value = ContentValues()
            value.put("price", 10.99)
            db.update("Book", value, "name = ?", arrayOf("The Da Vinci Code"))

//            db.execSQL("update Book set price = ? where name = ?", arrayOf(10.99, "The Da Vinci Code"))
        }
        //查询数据
        btnSelect.setOnClickListener {
            val db = helper.writableDatabase
            //查询表中所有数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    //遍历cursor对象，获取数据
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("TAG", "$name ,+ $author ,+ $pages ,+ $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
//            db.rawQuery("select * from Book", null)
        }
        //删除数据
        btnDelete.setOnClickListener {
            val db = helper.writableDatabase
            db.delete("Book", "page > ?", arrayOf("500"))
//            db.execSQL("delete from Book where page > ?", arrayOf("500"))
        }
        //数据库事务
        btnTransaction.setOnClickListener {
            val db = helper.writableDatabase
            db.beginTransaction()//开启事务
            try {
                db.delete("Book", null, null)
                if (true) {
                    //手动抛出一个异常，让事务失败
                    throw NullPointerException()
                }
                db.execSQL(
                    "insert into Book(name, author, pages, price) values(?,?,?,?)",
                    arrayOf("Game of Thrones", "George Martin", 720, 20.85)
                )
                //事务成功，完成操作
                db.setTransactionSuccessful()
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
//                结束事务，若事务失败，取消本次所有操作
                db.endTransaction()
            }
        }
    }
}