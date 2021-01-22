package com.xxyl.jetpacktest13

import java.lang.StringBuilder

/**
 * author: zhang
 * created on: 2021/1/5 15:04
 * description:
 */
/**
 * 单元格
 * @property content String
 */
class Td{
    var content = ""
    fun html() = "\n\t\t<td>$content</td>"
}
class Tr{
    private val children = ArrayList<Td>()
    fun td(block:Td.()->String){
        val td = Td()
        td.content = td.block()
        children.add(td)
    }
    fun html(): String{
        val builder = StringBuilder()
        builder.append("\n\t<tr>")
        for (childTag in children){
            builder.append(childTag.html())
        }
        builder.append("\n\t</tr>")
        return builder.toString()
    }
}
class Table{
    private val children = ArrayList<Tr>()
    fun tr(block: Tr.() -> Unit){
        val tr = Tr()
        tr.block()
        children.add(tr)
    }

    fun html(): String{
        val builder = StringBuilder()
        builder.append("<table>")
        for (childTag in children){
            builder.append(childTag.html())
        }
        builder.append("\n</table>")
        return builder.toString()
    }
}
fun table(block: Table.()->Unit): String{
    val table = Table()
    table.block()
    return table.html()
}

fun main() {
    val html = table {
        tr {
            td { "Apple" }
            td { "Grape" }
            td { "orange" }
        }
        tr {
            td { "Pear" }
            td { "Banana" }
            td { "Watermelon" }
        }
    }
    println(html)
}
