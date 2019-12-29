package com.apprenderchile.apprender.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.logica.Validator
import com.google.android.material.floatingactionbutton.FloatingActionButton
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
