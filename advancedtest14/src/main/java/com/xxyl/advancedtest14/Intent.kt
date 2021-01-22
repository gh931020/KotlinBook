package com.xxyl.advancedtest14

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * author: zhang
 * created on: 2021/1/5 15:36
 * description: 使用Intent传递对象
 */
/**
 * 1.Serializable方式
 * 序列化,表示将一个对象转换成可存储或可传输的状态.序列化后的对象可以在网络上进行传输,也可以存储到本地.
 * 这种传递对象的工作原理是先将一个对象序列化成可存储或可传输的状态,传递给另外一个Activity后再将其反序列化
 * 成一个新的对象.虽然这两个对象中存储的数据完全一致,但实际上他们是不同的对象.
 */
class Person: Serializable{
    val name =""
    val age = 0
}

/**
 * 2.Parcelable方式(推荐)
 * 将一个完整的对象进行分解,而分解后的每一部分都是Intent所支持的数据类型,这样就能实现传递对象的功能了
 */
class Teacher : Parcelable{
    var name = ""
    var age = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        //写出name
        parcel.writeString(name)
        //写出age
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Teacher> {
        override fun createFromParcel(parcel: Parcel): Teacher {
            val teacher = Teacher()
            //此处的读取顺序一定要和写出顺序相同
            //读取name
            teacher.name = parcel.readString()?:""
            //读取age
            teacher.age = parcel.readInt()
            return teacher
        }

        override fun newArray(size: Int): Array<Teacher?> {
            return arrayOfNulls(size)
        }
    }
}

//要求传递的所有数据都必须封装在对象的主构造函数中才行
@Parcelize
class Student(var name: String, var age: Int) : Parcelable
