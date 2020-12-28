package com.xxyl.servicetest10.service

import android.app.IntentService
import android.content.Intent
import android.util.Log


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions and extra parameters.
 */
class MyIntentService : IntentService("MyIntentService") {
    private val TAG = this.javaClass.simpleName
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: /n Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

}
