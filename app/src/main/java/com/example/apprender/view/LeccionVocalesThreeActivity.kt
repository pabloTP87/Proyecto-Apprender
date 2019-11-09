package com.example.apprender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.apprender.R
import com.example.apprender.view.fragments.LeccionCompleteOneFragment

class LeccionVocalesThreeActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion_vocales_three)

        createFragment()
    }

    private fun createFragment(){

        val transaction = manager.beginTransaction()
        val fragment = LeccionCompleteOneFragment()
        transaction.replace(R.id.leccion_vcls_three_container,fragment)
        transaction.commit()
    }
}
