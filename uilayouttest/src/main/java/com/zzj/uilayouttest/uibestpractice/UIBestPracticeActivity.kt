package com.zzj.uilayouttest.uibestpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzj.uilayouttest.R
import com.zzj.uilayouttest.bean.Msg
import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.activity_recycler.recyclerView
import kotlinx.android.synthetic.main.activity_uibestpractice.*

class UIBestPracticeActivity : AppCompatActivity() {

    companion object{
        fun start(activity: Activity){
            activity.startActivity(Intent(activity, UIBestPracticeActivity::class.java))
        }
    }

    private val msgList = ArrayList<Msg>()
    private lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uibestpractice)
        initMsg()
        adapter = MsgAdapter(msgList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        btnSend.setOnClickListener {
            val content = etMsg.text.toString()
            if (content.isNotEmpty()){
                val msg = Msg(content, Msg.TYPE_SENT)
                msgList.add(msg)
                adapter.notifyItemInserted(msgList.size -1)//当有新消息时，刷新RecyclerView中的显示
                recyclerView.scrollToPosition(msgList.size -1)//将RecyclerView定位到最后一行
                etMsg.setText("")//清空输入框内容
            }
        }
    }

    private fun initMsg() {
        msgList.apply {
            add(Msg("Hello guy.", Msg.TYPE_RECEIVED))
            add(Msg("Hello . Who is that?", Msg.TYPE_SENT))
            add(Msg("This is Tom, Nice talking to you .", Msg.TYPE_RECEIVED))
        }
    }
}