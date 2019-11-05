package com.example.apprender.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.example.apprender.R
import com.example.apprender.view.fragments.*

class RegistryActivity : AppCompatActivity(), RegistryNameFragment.IdatosUsuario,
    RegistryLastNameFragment.ILastNameSend, RegistryAgeFragment.IAgeSend,
    RegistryDniFragment.IRutSend, RegistryGenderFragment.IGenderSend {

    val manager = supportFragmentManager
    val bundle = Bundle(10)

    override fun generoUsuario(genero: String) {

        val fragment = RegistryConfirmFragment()
        bundle.putString("genero", genero)
        fragment.arguments = bundle

        createFragment(fragment)
    }

    override fun rutUsuario(rut: String) {

        val fragment = RegistryGenderFragment()
        bundle.putString("rut",rut)
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

        createRegistryFragment()
        checkPermision()
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

    private fun checkPermision(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
                startActivity(intent)
                finish()
                Toast.makeText(this,"permiso de microfono aceptado",Toast.LENGTH_SHORT).show()
            }
        }
    }

}
