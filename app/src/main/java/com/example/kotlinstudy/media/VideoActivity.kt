package com.example.kotlinstudy.media

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.little_white}")
        vv_video.setVideoURI(uri)
        btn_play_video.setOnClickListener {
            if (!vv_video.isPlaying) {
                vv_video.start()
            }
        }
        btn_pause_video.setOnClickListener {
            if (vv_video.isPlaying) {
                vv_video.pause()
            }
        }
        btn_replay_video.setOnClickListener {
            if (vv_video.isPlaying) {
                vv_video.resume()
            } else {
                vv_video.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        vv_video.suspend()
    }
}