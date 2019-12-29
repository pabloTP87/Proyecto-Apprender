package com.apprenderchile.apprender.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.apprenderchile.apprender.R

class ChapterThreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_three)
    }

    // Evento que finaliza esta actividad al presionar el boton atrás en el navbar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId

        if (id == android.R.id.home){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // evento que finaliza esta actividad al presionar el boton atrás del móvil
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
