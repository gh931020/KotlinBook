package com.xxyl.multimediatest9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.xxyl.multimediatest9.camera.CameraActivity
import com.xxyl.multimediatest9.music.MusicActivity
import com.xxyl.multimediatest9.notification.NotificationActivity
import com.xxyl.multimediatest9.video.PlayVideoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnNotification.setOnClickListener {
            NotificationActivity.start(this)
        }
        btnCamera.setOnClickListener {
            CameraActivity.start(this)
        }
        btnMusic.setOnClickListener {
            MusicActivity.start(this)
        }
        btnVideo.setOnClickListener {
            PlayVideoActivity.start(this)
        }
    }
}
