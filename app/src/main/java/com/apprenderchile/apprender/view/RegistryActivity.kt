package com.apprenderchile.apprender.view

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.view.fragments.*
import com.apprenderchile.apprender.interfaces.IDatosUsuario
import com.apprenderchile.apprender.logica.CustomDialog

class RegistryActivity : AppCompatActivity(), IDatosUsuario {

    private val manager = supportFragmentManager
    val bundle = Bundle(10)

    override fun generoUsuario(genero: String) {

        val fragment = RegistryConfirmFragment()
        bundle.putString("genero", genero)
        fragment.arguments = bundle

        createFragment(fragment)
    }

    override fun rutUsuario(rut: String, dv: String) {

        val fragment = RegistryGenderFragment()
        bundle.putString("rut", rut)
        bundle.putString("dv", dv)
        fragment.arguments = bundle

        createFragment(fragment)
    }

    override fun userAge(edad: Int) {

        val fragment = RegistryDniFragment()
        bundle.putInt("edad", edad)
        fragment.arguments = bundle

        createFragment(fragment)
    }

    override fun lastName(lastName: String) {

        val fragment = RegistryAgeFragment()
        bundle.putString("apepat", lastName)
        fragment.arguments = bundle

        createFragment(fragment)
    }
    override fun nombreUsuario(nombre: String) {

        val fragment = RegistryLastNameFragment()
        bundle.putString("nombre",nombre)
        fragment.arguments = bundle

        createFragment(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry)

        val backButton = findViewById<ImageButton>(R.id.btn_close_registry)

        createRegistryFragment()

        backButton.setOnClickListener {

            showCloseDialog()
        }
    }

    private fun createRegistryFragment(){

        val transaction = manager.beginTransaction()
        val fragment = RegistryNameFragment()
        transaction.replace(R.id.fragmentContainer,fragment)
        transaction.commit()
    }

    private fun createFragment(fragment: Fragment){

        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
        transaction.replace(R.id.fragmentContainer,fragment)
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
            .setDescripcion("¿deseas terminar tu registro?")
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
