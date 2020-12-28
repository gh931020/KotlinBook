package com.xxyl.servicetest10.async

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.xxyl.servicetest10.R
import kotlinx.android.synthetic.main.activity_async.*
import java.lang.Exception

class AsyncActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, AsyncActivity::class.java)
            context.startActivity(starter)
        }
    }
    private var progressInt = 0
    private lateinit var myContext:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
        myContext = this
        btnStart.setOnClickListener {
            MyAsyncTask().execute()
        }
    }

    inner class MyAsyncTask() : AsyncTask<Unit, Int, Boolean>() {
        override fun onPreExecute() {
            super.onPreExecute()
            Toast.makeText(myContext, "Start Loading", Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg params: Unit?): Boolean =
            try {
                while (true) {
                    progressInt += 1
                    publishProgress(progressInt)
                    Thread.sleep(500)
                    if (progressInt >= 100) break
                }
                true
            } catch (e: Exception) {
                false
            }


        override fun onProgressUpdate(vararg values: Int?) {
            progressBar.setProgress(values[0]!!)
            tvProgress.text = resources.getString(R.string.progress,values[0])
        }

        override fun onPostExecute(result: Boolean) {
            if (result){
                Toast.makeText(myContext, "Load Success", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(myContext, "Load Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}