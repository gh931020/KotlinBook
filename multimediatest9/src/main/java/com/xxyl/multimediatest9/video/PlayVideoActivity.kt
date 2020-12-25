package com.xxyl.multimediatest9.video

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.multimediatest9.R
import kotlinx.android.synthetic.main.activity_play_video.*

class PlayVideoActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, PlayVideoActivity::class.java)
            context.startActivity(starter)
        }
    }
    //VideoPlay无法播放assets文件夹下的视频，需要将文件防止res/raw文件夹下
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)
        //获取raw文件夹下的视频资源【res内所有的文件都会生成对应的唯一的 resId】
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)

        btnPlayV.setOnClickListener {
            if (videoView.isPlaying.not()){
                videoView.start()
            } //开始播放
        }

        btnPauseV.setOnClickListener {
            if (videoView.isPlaying) videoView.pause() //暂停播放
        }

        btnReplayV.setOnClickListener {
            if (videoView.isPlaying) videoView.resume() else videoView.start()//重新播放
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //内部调用release方法释放资源
        videoView.suspend()
    }
}