package com.example.apprender.view

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.apprender.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnAudioLogin = findViewById<Button>(R.id.btnAudioLogin)

        val audioLogin = MediaPlayer.create(this,R.raw.audio_login)

        btnAudioLogin.setOnClickListener {
            audioLogin.seekTo(0)
            audioLogin.start()
        }

        btn_ingresar.setOnClickListener {

            val intent = Intent(this,MainActivity::class.java)

            if(audioLogin.isPlaying){
                audioLogin.pause()
            }

            startActivity(intent)
        }
        btn_registro.setOnClickListener {

            val intent: Intent =(Intent(this,StartActivity::class.java))

            if(audioLogin.isPlaying){
                audioLogin.pause()
            }

            startActivity(intent)
        }
    }
}
