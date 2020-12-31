package com.xxyl.networktest11.xmlparse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import com.xxyl.networktest11.R
import kotlinx.android.synthetic.main.activity_xml_parse.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xml.sax.Attributes
import org.xml.sax.InputSource
import org.xml.sax.helpers.DefaultHandler
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.lang.Exception
import java.lang.StringBuilder
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class XmlParseActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private var responseData: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_parse)
        btnSendRequest.setOnClickListener {
            sendRequestByOkHttp()
        }
        btnPullParse.setOnClickListener {
            pullParse(responseData)
        }
        btnSaxParse.setOnClickListener {
            saxParser(responseData)
        }
    }

    /**
     * SAX解析
     * @param data String?
     * @return Unit
     */
    private fun saxParser(data: String?) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = MyHandler()
            //将handler设置到xmlReader中
            xmlReader.contentHandler = handler
            //开始执行解析
            xmlReader.parse(InputSource(StringReader(data)))
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    /**
     * Pull解析
     * @param data String?
     * @return Unit
     */
    private fun pullParse(data: String?) {
        try {
            //创建pull解析工厂
            val factory = XmlPullParserFactory.newInstance()
            //获取pullParser解析对象
            val xmlPullParser = factory.newPullParser()
            //
            xmlPullParser.setInput(StringReader(data))
            var eventType = xmlPullParser.eventType
            var id = ""
            var name = ""
            var version = ""
            while (eventType != XmlPullParser.END_DOCUMENT){
                //获取当前节点的名字
                val nodeName = xmlPullParser.name
                when(eventType){
                    //开始解析某个节点
                    XmlPullParser.START_TAG ->{
                        when (nodeName) {
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                            else -> {
                            }
                        }
                    }
                    //结束解析某个节点
                    XmlPullParser.END_TAG ->{
                        if ("app" == nodeName){
                            Log.d(TAG, "id : $id ")
                            Log.d(TAG, "name : $name ")
                            Log.d(TAG, "version : $version ")
                        }

                    }
                }
                eventType = xmlPullParser.next()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun sendRequestByOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2/get_data.xml")
                    .build()
                val response = client.newCall(request).execute()
                responseData = response.body?.string()
                responseData?.let { showResponseData(it) }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    private fun showResponseData(responseData: String) {
        runOnUiThread {
            tvXmlResponse.text = responseData
        }
    }
}

class MyHandler : DefaultHandler(){
    private val TAG = this.javaClass.simpleName
    private var nodeName = ""
    private lateinit var id: StringBuilder
    private lateinit var name: StringBuilder
    private lateinit var version: StringBuilder
    //开始解析xml时调用
    override fun startDocument() {
        Log.d(TAG, "startDocument: ")
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }
    //开始解析某个节点的时候调用
    override fun startElement(
        uri: String,
        localName: String,
        qName: String,
        attributes: Attributes
    ) {
        Log.d(TAG, "startElement: ")
        //记录当前节点名称
        nodeName = localName
        Log.d(TAG, "uri is $uri")
        Log.d(TAG, "localName is $localName")
        Log.d(TAG, "qName is $qName")
        Log.d(TAG, "attributes is $attributes")
    }
    //获取节点中内容的时候调用
    override fun characters(ch: CharArray, start: Int, length: Int) {
        Log.d(TAG, "characters: ")
        //根据当前节点名判断将内容添加到哪一个StringBuilder对象中
        when(nodeName){
            "id" -> id.append(ch, start, length)
            "name" -> name.append(ch, start, length)
            "version" -> version.append(ch, start, length)
        }
    }
    //完成解析某个节点的时候调用
    override fun endElement(uri: String, localName: String, qName: String) {
        Log.d(TAG, "endElement: ")
        if ("app" == localName){
            Log.d(TAG, "id is ${id.toString().trim()}")
            Log.d(TAG, "name is ${name.toString().trim()}")
            Log.d(TAG, "version is ${version.toString().trim()}")
            //将builder清空
            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }
    //完成整个XML解析的时候调用
    override fun endDocument() {
        Log.d(TAG, "endDocument: 解析完毕")
    }
}