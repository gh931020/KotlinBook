package com.zzj.uilayouttest.uibestpractice

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zzj.uilayouttest.R
import kotlinx.android.synthetic.main.item_msg_left.view.*

/**
 * author: zhang
 * created on: 2020/12/22 14:52
 * description:密封类，主要用于when判断中可验证条件完整性，省去else分支
 */
sealed class MsgViewHolder(view: View): RecyclerView.ViewHolder(view)
class ViewHolderLeft(view: View): MsgViewHolder(view){
    val tvMsgLeft: TextView = view.findViewById<TextView>(R.id.tvMsgLeft)
}
class ViewHolderRight(view: View): MsgViewHolder(view){
    val tvMsgRight: TextView = view.findViewById<TextView>(R.id.tvMsgRight)
}