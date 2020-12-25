package com.xxyl.datasavetest7.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.xxyl.datasavetest7.db.SQLiteHelper

class DatabaseProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3
    private val authority = "com.xxyl.datasavetest7.provider"
    private var dbHelper: SQLiteHelper? = null

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, "book", bookDir)
        matcher.addURI(authority, "book/#", bookItem)
        matcher.addURI(authority, "category", categoryDir)
        matcher.addURI(authority, "category/#", categoryItem)
        matcher
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int =
        dbHelper?.let {
            val db = it.writableDatabase
            val deleteId = when (uriMatcher.match(uri)) {
                bookDir -> db.delete("Book", selection, selectionArgs)
                bookItem -> {
                    //[0]: path路径   [1]: id
                    val bookId = uri.pathSegments[1]
                    db.delete("Book", "id = ?", arrayOf(bookId))
                }
                categoryDir -> db.delete("Category", selection, selectionArgs)
                categoryItem -> {
                    val categoryId = uri.pathSegments[1]
                    db.delete("Category", "id = ?", arrayOf(categoryId))
                }
                else -> 0
            }
            deleteId
        } ?: 0

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        bookDir -> "vnd.android.cursor.dir/vnd.com.xxyl.datasavetest7.provider.book"
        bookItem -> "vnd.android.cursor.item/vnd.com.xxyl.datasavetest7.provider.book"
        categoryDir -> "vnd.android.cursor.dir/vnd.com.xxyl.datasavetest7.provider.category"
        categoryItem -> "vnd.android.cursor.item/vnd.com.xxyl.datasavetest7.provider.category"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("Book", null, values)
                Uri.parse("content://$authority/book/$newBookId")
            }
            categoryDir, categoryItem -> {
                val newCategoryId = db.insert("Category", null, values)
                Uri.parse("content://$authority/category/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    override fun onCreate(): Boolean = context?.let {
        dbHelper = SQLiteHelper(it, "BookStore.db", 2)
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? = dbHelper?.let {
        //查询数据
        val db = it.readableDatabase
        val cursor = when (uriMatcher.match(uri)) {
            bookDir -> db.query("Book", projection, selection, selectionArgs, null, null, sortOrder)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.query("Book", projection, "id = ?", arrayOf(bookId), null, null, sortOrder)
            }
            categoryDir -> db.query(
                "Category",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
            )
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query(
                    "Category",
                    projection,
                    "id = ?",
                    arrayOf(categoryId),
                    null,
                    null,
                    sortOrder
                )
            }
            else -> null
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int = dbHelper?.let {
        val db = it.writableDatabase
        val result = when (uriMatcher.match(uri)) {
            bookDir -> db.update("Book", values, selection, selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("Book", values, "id = ?", arrayOf(bookId))
            }
            categoryDir -> db.update("Category", values, selection, selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category", values, "id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        result
    } ?: 0
}
