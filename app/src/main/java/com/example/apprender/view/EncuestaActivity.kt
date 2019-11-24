package com.example.apprender.view

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.widget.ImageButton
import com.example.apprender.R
import com.example.apprender.interfaces.ISendEncuesta
import com.example.apprender.view.fragments.*
import kotlinx.android.synthetic.main.leccion_close_dialog.view.*

class EncuestaActivity : AppCompatActivity() , ISendEncuesta {

    private val manager = supportFragmentManager
    val bundle = Bundle(5)

    override fun enviarEncuestaOne(respuesta: String) {
        val fragment = EncuestaTwoFragment()
        bundle.putString("respuesta_one", respuesta)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun enviarEncuestaTwo(respuesta: String) {
        val fragment = EncuestaThreeFragment()
        bundle.putString("respuesta_two", respuesta)
        fragment.arguments = bundle
        createFragment(fragment)    }

    override fun enviarEncuestaThree(respuesta: String) {
        val fragment = EncuestaConfirmFragment()
        bundle.putString("respuesta_three", respuesta)
        fragment.arguments = bundle
        createFragment(fragment)    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        val backButton = findViewById<ImageButton>(R.id.btn_close_encuesta)

        createEncuestaFragment()

        backButton.setOnClickListener {

            showCloseDialog()
        }
    }

    private fun createEncuestaFragment(){

        val transaction = manager.beginTransaction()
        val fragment = EncuestaOneFragment()
        transaction.replace(R.id.fragment_encuesta_container,fragment)
        transaction.commit()
    }

    private fun createFragment(fragment: Fragment){

        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
        transaction.replace(R.id.fragment_encuesta_container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        showCloseDialog()
    }

    private fun showCloseDialog() {
        val confirmDialog = LayoutInflater.from(this).inflate(R.layout.encuesta_close_dialog,null)
        val builder = AlertDialog.Builder(this).setView(confirmDialog)

        val alertDialog = builder.show()

        alertDialog.setCanceledOnTouchOutside(false)

        confirmDialog.btn_si.setOnClickListener {
            alertDialog.dismiss()
            this.finish()
        }

        confirmDialog.btn_no.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
