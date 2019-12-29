package com.apprenderchile.apprender.view

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.interfaces.ISendEncuesta
import com.apprenderchile.apprender.logica.CustomDialog
import com.apprenderchile.apprender.view.fragments.*

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
        val customDialog = CustomDialog.Builder()
            .setImagen(R.drawable.ic_close_leccion)
            .setTitulo("Perderás tu avance")
            .setDescripcion("¿deseas terminar la encuesta?")
            .setContinueButtonVisible(false)
            .setContinueButtonText("")
            .setPositiveButtonText("Si")
            .setCancelButtonText("No")
            .build()

        customDialog.show(supportFragmentManager,"Custom close dialog")
        customDialog.isCancelable = false

        customDialog.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
            override fun onPositiveButtonClick() {
                customDialog.dismiss()
                finish()
            }

            override fun onCancelButtonClick() {
                customDialog.dismiss()
            }

            override fun onContinueButtonClick() {
            }

        })
    }
}
