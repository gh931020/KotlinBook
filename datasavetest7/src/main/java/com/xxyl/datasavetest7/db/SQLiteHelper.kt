package com.xxyl.datasavetest7.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * author: zhang
 * created on: 2020/12/24 9:32
 * description:
 */
class SQLiteHelper(
    val context: Context,
    val dbName: String,
    val version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {
    private val sqlBook =
        "create table Book ( id integer primary key autoincrement, author text, price real, pages integer, name text, category_id integer)"
    private val sqlCategory =
        "create table Category( id integer primary key autoincrement, category_name text, category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(sqlBook)
        db.execSQL(sqlCategory)
//        Toast.makeText(context, "Create success!!!", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //如果表已经存在，就先删除
//        db.execSQL("drop table if exists Book")
//        db.execSQL("drop table if exists Category")
//        onCreate(db)
        //只有在升级时当前版本号小于等于1才去创建
        if (oldVersion <= 1) {
            db.execSQL(sqlCategory)
        }
        if (oldVersion<=2){
            db.execSQL("alter table Book add column category_id integer")
        }
    }
}