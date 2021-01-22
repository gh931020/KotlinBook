package com.xxyl.jetpacktest13.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.xxyl.jetpacktest13.R
import kotlinx.android.synthetic.main.activity_work_manager.*
import java.util.concurrent.TimeUnit

/**
 * WorkManager只是一个处理定时任务的工具,它可以保证即使在应用退出甚至手机重启的情况下,之前注册的任务仍然将会
 * 得到执行,因此WorkManager很适合由于执行一些定期和服务器进行交互的任务,比如周期性地同步数据,等等.
 * 另外,使用WorkManager注册的周期性任务不能保证一定会准时执行,这并不是bug,而是系统为了减少电量消耗,可能会将
 * 触发时间临近的几个任务放在一起执行,这样可以大幅度的减少cpu被唤醒的次数,从而有效延长电池的使用时间.
 *
 * 1.定义一个后台任务,并实现具体的任务逻辑;
 * 2.配置该后台任务的运行条件和约束信息,并构建后台任务请求
 * 3.将该后台任务请求传入WorkManager的enqueue()方法中,系统会在合适的时间运行
 */
class WorkManagerActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        btnDoWork.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWork::class.java)
                .setInitialDelay(5, TimeUnit.SECONDS)//指定任务在5秒钟之后执行
                .addTag("zzj")//为任务添加标签,可根据标签来关闭任务
                //如果doWork()返回了Result.retry(), 可以结合setBackoffCriteria()方法来重新执行任务
                //LINEAT 多次失败重试时间间隔以线性方式增加; EXPONENTIAL: 以指数方式增加
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()
            WorkManager.getInstance(this).enqueue(request)
            //监听任务的执行结果(根据dowork中的返回值判断 Result.success 或者 Result.failed)
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id).observe(this, Observer {workInfo ->
                when(workInfo.state){
                    WorkInfo.State.SUCCEEDED -> Log.d(TAG, "do work success ")
                    WorkInfo.State.FAILED -> Log.d(TAG, "do work failed ")
                    else -> {}
                }
            })
            //根据任务id取消
//            WorkManager.getInstance(this).cancelWorkById(request.id)
            //关闭同一标签组的所有任务
//            WorkManager.getInstance(this).cancelAllWorkByTag("zzj")
            //关闭所有任务
//            WorkManager.getInstance(this).cancelAllWork()

        }
    }
}