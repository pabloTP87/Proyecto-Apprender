package com.example.apprender.view

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apprender.R
import com.example.apprender.logica.Session
import com.example.apprender.viewmodel.FirestoreViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var viewModel = FirestoreViewModel()
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(FirestoreViewModel::class.java)

        session = Session(this)

        if (session.isLoggedIn()){
            val intent = Intent(this,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            this.startActivity(intent)
            finish()
        }
        val btnAudioLogin = findViewById<Button>(R.id.btnAudioLogin)

        val audioLogin = MediaPlayer.create(this,R.raw.audio_login)

        btnAudioLogin.setOnClickListener {
            audioLogin.seekTo(0)
            audioLogin.start()
        }

        btn_ingresar.setOnClickListener {

            val rut = txt_rut.text.toString().trim()

            if (rut.isEmpty()){
                Toast.makeText(this,"Ingresa tu contraseña",Toast.LENGTH_SHORT).show()
            } else{
                login_save_charge.indeterminateDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
                login_save_charge.visibility = View.VISIBLE
                viewModel.validarUsuario(rut)
            }

            if(audioLogin.isPlaying){
                audioLogin.pause()
            }
        }

        validarLoginUsuario()

        btn_registro.setOnClickListener {

            val intent: Intent =(Intent(this,StartActivity::class.java))

            if(audioLogin.isPlaying){
                audioLogin.pause()
            }

            startActivity(intent)
        }
    }

    private fun validarLoginUsuario(){
        viewModel.fetchDataComplition().observe(this, Observer {
            if (it){
                login_save_charge.visibility = View.INVISIBLE
                getSessionData()
            }else{
                login_save_charge.visibility = View.INVISIBLE
                Toast.makeText(this,"Contraseña incorrecta",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getSessionData(){
        viewModel.fetchLoginData().observe(this, Observer {
            val rut = it[0] // Recuperamos el rut desde el Mutable List
            val nombre = it[1] // Recuperamos el nombre desde el Mutable List

            session.createLoginSession(nombre, rut) // Creamos las variables de sesión
            // Pasamos al Main Activity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Bienvenido $nombre",Toast.LENGTH_SHORT).show()
            finish()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        this.finish()
    }
}
