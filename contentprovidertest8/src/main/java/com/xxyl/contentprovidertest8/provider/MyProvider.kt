package com.xxyl.contentprovidertest8.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

/**
 * author: zhang
 * created on: 2020/12/24 16:11
 * description:
 */
class MyProvider: ContentProvider() {
    //匹配成功的返回值
    //表1所有数据
    private val table1Dir = 0
    //表1单条数据
    private val table1Item = 1
    //表2所有数据
    private val table2Dir = 2
    //表2单条数据
    private val table2Item = 3

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    init {
        //uri = authority（包名+provider）+ path（路径：要查询的表，可跟通配符*表示查询所有，#表示查询指定id【数字】）
        uriMatcher.addURI("com.xxyl.contentprovidertest8.provider", "table1", table1Dir)
        uriMatcher.addURI("com.xxyl.contentprovidertest8.provider", "table1/#", table1Item)
        uriMatcher.addURI("com.xxyl.contentprovidertest8.provider", "table2", table2Dir)
        uriMatcher.addURI("com.xxyl.contentprovidertest8.provider", "table2/#", table2Item)
    }
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when(uriMatcher.match(uri)){
            table1Dir ->{
                //查询表1中的所有数据
            }
            table1Item ->{
                //查询表1中的单条数据
            }
            table2Dir ->{
                //查询表2中的所有数据
            }
            table2Item ->{
                //查询表2中的单条数据
            }
        }
        return null
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }
    /**
     * MIME
     * 1.必须以vnd开头
     * 2.如果内容URI以路径结尾，则后接android.cursor.dir/；如果内容URI以id结尾，则后接android.cursor.item/
     * 3.最后接上vnd.<authority>.<path>
     * ex：content://com.example.app.provider/table1  ->
     *     vnd.android.cursor.dir/vnd.com.example.app.provider.table1
     *     content://com.example.app.provider/table1/1  ->
     *     vnd.android.cursor.item/vnd.com.example.app.provider.table1
     */
    override fun getType(uri: Uri): String? = when(uriMatcher.match(uri)){
        table1Dir -> "vnd.android.cursor.dir/vnd.com.xxyl.contentprovidertest8.provider.table1"
        table1Item -> "vnd.android.cursor.item/vnd.com.xxyl.contentprovidertest8.provider.table1"
        table2Dir -> "vnd.android.cursor.dir/vnd.com.xxyl.contentprovidertest8.provider.table2"
        table2Item -> "vnd.android.cursor.item/vnd.com.xxyl.contentprovidertest8.provider.table2"
        else -> null
    }
}