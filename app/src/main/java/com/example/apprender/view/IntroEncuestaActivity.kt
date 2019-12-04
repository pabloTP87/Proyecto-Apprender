package com.example.apprender.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.apprender.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class IntroEncuestaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_encuesta)

        val btnAudioReg = findViewById<FloatingActionButton>(R.id.btn_audio_intro_encuesta)
        val backLogin = findViewById<ImageButton>(R.id.btn_back_chapter)
        val btnComenzar = findViewById<Button>(R.id.btn_comenzar_encuesta)

        backLogin.setOnClickListener {
            this.finish()
        }

        btnComenzar.setOnClickListener {
            val intent = Intent(this, EncuestaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        this.finish()
    }
}
