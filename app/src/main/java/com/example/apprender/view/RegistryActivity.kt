package com.example.apprender.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apprender.R
import com.example.apprender.view.fragments.*
import com.example.apprender.interfaces.IDatosUsuario
import kotlinx.android.synthetic.main.leccion_close_dialog.view.*

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
        val confirmDialog = LayoutInflater.from(this).inflate(R.layout.registry_close_dialog,null)
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
