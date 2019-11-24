package com.example.apprender.view

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.ImageButton
import com.example.apprender.R
import com.example.apprender.logica.Validator
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    val validator : Validator = Validator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        validator.checkPermision(this , this)

        val btnAudioReg = findViewById<FloatingActionButton>(R.id.btnAudioRegister)
        val backLogin = findViewById<ImageButton>(R.id.btnBackLogin)

        val audioRegistro = MediaPlayer.create(this,R.raw.audio_registro)

        btnAudioReg.setOnClickListener {

            audioRegistro.seekTo(0)
            audioRegistro.start()
        }

        backLogin.setOnClickListener {

            if (audioRegistro.isPlaying){
                audioRegistro.pause()
            }
            this.finish()
        }

        btnComenzar.setOnClickListener {

            val intent = Intent(this, RegistryActivity::class.java)

            if (audioRegistro.isPlaying){
                audioRegistro.pause()
            }

            startActivity(intent)
        }
    }
}
