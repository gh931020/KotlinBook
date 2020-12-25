package com.xxyl.datasavetest7

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_file.*
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

class FileActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, FileActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        btnCommit.setOnClickListener {
            val content = etFile.text.toString()
            commit(content)
        }
        btnRead.setOnClickListener {
            tvContent.setText(read())
        }
    }

    private fun read(): String {
        val content = StringBuilder()
        try {
            val inputStream = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {
                it.forEachLine {
                    content.append(it)
                }
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
        return content.toString()
    }

    private fun commit(content: String) {
        try {
            //"data"文件名
            //创建输出流；
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            //构建Writer对象，构建BufferWriter对象
            val writer = BufferedWriter(OutputStreamWriter(output))
            //输出文件内容
            writer.use {
                it.write(content)
            }
            etFile.setText("")
            Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show()
        }catch (e: IOException){
            e.printStackTrace()
            Toast.makeText(this, "存储失败", Toast.LENGTH_SHORT).show()
        }
    }
}