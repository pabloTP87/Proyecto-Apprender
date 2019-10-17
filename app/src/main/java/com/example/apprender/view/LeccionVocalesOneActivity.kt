package com.example.apprender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.apprender.R
import com.example.apprender.view.fragments.LeccionSelectAFragment

class LeccionVocalesOneActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion_vocales_one)

        createFragment()
    }

    fun createFragment(){

        val transaction = manager.beginTransaction()
        val fragment = LeccionSelectAFragment()
        transaction.replace(R.id.leccion_vcls_one_container,fragment)
        transaction.commit()
    }
}
