package com.xxyl.jetpacktest13.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * author: zhang
 * created on: 2021/1/5 14:30
 * description:
 */
class SimpleWork(context: Context, params: WorkerParameters): Worker(context, params) {
    private val TAG = this.javaClass.simpleName
    override fun doWork(): Result {
        Log.d(TAG, "doWork: in SimpleWork")
        return Result.success()
    }
}