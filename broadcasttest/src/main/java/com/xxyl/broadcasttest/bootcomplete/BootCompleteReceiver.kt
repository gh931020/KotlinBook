package com.xxyl.broadcasttest.bootcomplete

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootCompleteReceiver : BroadcastReceiver() {
    /**
     * 静态注册，监听开机广播
     * @param context Context
     * @param intent Intent
     * @return Unit
     */
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Toast.makeText(context, "boot complete receiver!!!", Toast.LENGTH_SHORT).show()
    }
}
