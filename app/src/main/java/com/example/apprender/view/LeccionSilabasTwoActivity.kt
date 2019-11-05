package com.example.apprender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.apprender.R
import com.example.apprender.view.fragments.MarSilabasOneFragment

class LeccionSilabasTwoActivity : AppCompatActivity() {

    var manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion_silabas_two)


        createFragment()
    }

    private fun createFragment(){

        val transaction = manager.beginTransaction()
        val fragment = MarSilabasOneFragment()
        transaction.add(R.id.leccion_slbs_two_container,fragment)
        transaction.commit()
    }
}
