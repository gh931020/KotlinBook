package com.xxyl.jetpacktest13.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.xxyl.jetpacktest13.room.dao.BookDao
import com.xxyl.jetpacktest13.room.dao.UserDao
import com.xxyl.jetpacktest13.room.entity.Book
import com.xxyl.jetpacktest13.room.entity.User

/**
 * author: zhang
 * created on: 2021/1/5 11:24
 * description:
 * @Database注解中声明了数据库的版本号,以及包含了哪些实体类.多个实体类之间用逗号隔开
 * 在伴生类中使用单例来获取database对象
 */
//@Database(version = 1, entities = [User::class])
//@Database(version = 2, entities = [User::class, Book::class])
@Database(version = 3, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao

    companion object {
        //数据库版本升级  1  -->  2,新建Book表
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book (id integer primary key autoincrement not null ,name text not null, pages integer not null)")
            }
        }
        //数据库版本升级  2  --> 3,Book表新增author列
        val MIGRATION_2_3 = object : Migration(2,3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "app_database"
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
                .apply {
                    instance = this
                }
        }
    }

}