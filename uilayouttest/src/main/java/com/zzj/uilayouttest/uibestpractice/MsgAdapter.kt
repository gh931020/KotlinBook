package com.zzj.uilayouttest.uibestpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zzj.uilayouttest.R
import com.zzj.uilayouttest.bean.Msg
import kotlinx.android.synthetic.main.item_msg_left.view.*
import java.lang.IllegalStateException

/**
 * author: zhang
 * created on: 2020/12/22 14:04
 * description:
 */
class MsgAdapter(val data: List<Msg>) : RecyclerView.Adapter<MsgViewHolder>() {

//    inner class ViewHolderLeft(view: View) : RecyclerView.ViewHolder(view) {
//        val tvMsgLeft = view.findViewById<TextView>(R.id.tvMsgLeft)
//    }
//
//    inner class ViewHolderRight(view: View) : RecyclerView.ViewHolder(view) {
//        val tvMsgRight = view.findViewById<TextView>(R.id.tvMsgRight)
//    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder =
        if (viewType == Msg.TYPE_RECEIVED) {
            ViewHolderLeft(
                LayoutInflater.from(parent.context).inflate(R.layout.item_msg_left, parent, false)
            )
        } else {
            ViewHolderRight(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_msg_right, parent, false)
            )
        }


    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
       val msg = data[position]
        when(holder){
           is ViewHolderLeft -> holder.tvMsgLeft.text = msg.content
            is ViewHolderRight -> holder.tvMsgRight.text = msg.content
//            else -> throw IllegalStateException()
       }
    }


}