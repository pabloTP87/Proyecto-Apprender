package com.example.apprender.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import android.widget.ImageButton
import com.example.apprender.R

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
