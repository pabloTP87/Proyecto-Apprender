package com.example.apprender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.apprender.R
import com.example.apprender.view.fragments.LeccionImgAFragment
import com.example.apprender.view.fragments.LeccionSelectAFragment

class LeccionVocalesTwoActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion_vocales_two)

        createFragment()
    }

    private fun createFragment(){

        val transaction = manager.beginTransaction()
        val fragment = LeccionImgAFragment()
        transaction.replace(R.id.leccion_vcls_two_container,fragment)
        transaction.commit()
    }
}
