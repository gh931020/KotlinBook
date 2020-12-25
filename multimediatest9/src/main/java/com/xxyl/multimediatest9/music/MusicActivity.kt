package com.xxyl.multimediatest9.music

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxyl.multimediatest9.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_music.*

/**
 *
 * @property mediaPlayer MediaPlayer
 */
class MusicActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, MusicActivity::class.java)
            context.startActivity(starter)
        }
    }

    /**
     * 音频播放步骤
     * setDataSource -> prepare -> start -> pause
     * reset:将media player充值到刚刚创建的状态，需要重新设置资源
     * stop:调用后的Media player对象无法再播放音频
     * release：释放资源
     * seekTo： 从指定位置开始播放
     */
    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)
        initMediaPlayer()//加载资源
        btnPlay.setOnClickListener {
            if (mediaPlayer.isPlaying.not()){
                mediaPlayer.start()
            }
        }
        btnPause.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }
        btnStop.setOnClickListener {
            if (mediaPlayer.isPlaying){
                mediaPlayer.reset()
                initMediaPlayer()//初始化资源
            }
        }
    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("music.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
//        释放资源
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}