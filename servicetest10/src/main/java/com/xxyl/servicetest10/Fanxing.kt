package com.xxyl.servicetest10

import android.content.Context
import android.content.Intent
import android.content.LocusId
import androidx.core.content.contentValuesOf
import java.sql.Struct

/**
 * author: zhang
 * created on: 2020/12/28 16:26
 * description:泛型
 * 类型擦除：指定的泛型只在编译器指定传入类型，但在JVM中无效，如：List<String>只在编译器指定必须传入String类型
 *          但是JVM中只存在List，不包含数据类型
 * 泛型实化：inline fun <reified T> getGenericType(){}
 */
inline fun <reified T> getGenericType() = T::class.java
fun main() {
    //可以通过内联函数获取到指定泛型的实际类型
    val result1 = getGenericType<String>()
    val result2 = getGenericType<Int>()
    println("result1 is $result1")  //result1 is java.lang.String
    println("result2 is $result2") //result2 is java.lang.Integer
    //协变，通过指定out类型，规定只可通过构造函数传入指定类型参数，无法后续更改保证类型转换安全
    val student = Student("110", "zzj", 12)
    val data = SimpleData<Student>(student)
    handleMyData(data)
    val studentData = data.get()
    //逆变
    val trans = object :Transform<Person>{
        override fun transform(t: Person): String {
            return "${t.name} ${t.age}"
        }
    }
    //通过逆变，Transform<Person>已经成为Transform<Student>的子类，因此这里传入不会报错
    handleTransformer(trans)
}

fun handleMyData(data: SimpleData<Person>) {
    val person = data.get()//由于Person是Student的父类，因此这里不会出现向上转型的问题
}


inline fun <reified T> startActivity(context: Context, block:Intent.()->Unit){
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}
// 泛型协变：假如定义了一个MyClass<T>的泛型类，其中A是B的子类型，同时MyClass<A>又是MyClass<B>的子类型，
// 那么我们就称MyClass在T这个泛型上是协变的.既使用out关键字指定泛型只能为输出类型，在类内无法更改，因此
// 不会出现类型转换的问题，保证了类型安全。
class SimpleData<out T>(val data:T?){
    fun get():T?{
        return data
    }
}
// 泛型逆变：假如定义了一个MyClass<T>的泛型类，其中A是B的子类型，同时MyClass<B>又是MyClass<A>的子类型，那么
// 我们就可以成MyClass在T这个泛型上是逆变的。
interface Transform<in T>{
    fun transform(t: T): String
}

fun handleTransformer(trans: Transform<Student>){
    val student = Student("213","sd", 12)
    val result = trans.transform(student)
}



open class Person(var name: String, var age: Int ){}

class Student(var id: String, name: String, age: Int): Person(name, age){}