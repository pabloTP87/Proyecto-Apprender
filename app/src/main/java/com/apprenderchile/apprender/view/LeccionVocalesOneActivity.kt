package com.apprenderchile.apprender.view

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.interfaces.ILeccionVocalesOne
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.logica.CustomDialog
import com.apprenderchile.apprender.view.fragments.*

class LeccionVocalesOneActivity : AppCompatActivity() ,
    ILeccionVocalesOne, VocalesOneConfirmFragment.sendTimeChronometer {

    private lateinit var chronometer: Chronometer
    private var timeStop: Long = 0

    private val manager = supportFragmentManager
    var bundle = Bundle(15)

    override fun stopTimer() {
        val fragment = VocalesOneConfirmFragment()
        chronometer.stop()
        val timer = (SystemClock.elapsedRealtime() - chronometer.base) / 1000

        bundle.putLong("tiempo", timer)
        fragment.arguments = bundle
    }

    override fun datosLeccionOne(puntaje: Int, acierto: Boolean) {

        val fragment = LeccionSelectEFragment()
        bundle.putInt("puntaje_a", puntaje)
        bundle.putBoolean("acierto_a", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionTwo(puntaje: Int, acierto: Boolean) {

        val fragment = LeccionSelectIFragment()
        bundle.putInt("puntaje_e", puntaje)
        bundle.putBoolean("acierto_e", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionThree(puntaje: Int, acierto: Boolean) {

        val fragment = LeccionSelectOFragment()
        bundle.putInt("puntaje_i", puntaje)
        bundle.putBoolean("acierto_i", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionFour(puntaje: Int, acierto: Boolean) {

        val fragment = LeccionSelectUFragment()
        bundle.putInt("puntaje_o", puntaje)
        bundle.putBoolean("acierto_o", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionFive(puntaje: Int, acierto: Boolean) {

        val fragment = VocalesOneConfirmFragment()
        bundle.putInt("puntaje_u", puntaje)
        bundle.putBoolean("acierto_u", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion_vocales_one)

        val backButton = findViewById<ImageButton>(R.id.back_button)


        createInitFragment()

        chronometer = findViewById(R.id.timer)
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

        backButton.setOnClickListener {

            timeStop = chronometer.base - SystemClock.elapsedRealtime()
            chronometer.stop()
            showCloseDialog()
        }

    }

    private fun createInitFragment(){

        val transaction = manager.beginTransaction()
        val fragment = LeccionSelectAFragment()
        transaction.replace(R.id.leccion_vcls_one_container,fragment)
        transaction.commit()
    }

    private fun createFragment(fragment: Fragment){

        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
        transaction.replace(R.id.leccion_vcls_one_container,fragment)
        //transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onPause() {
        super.onPause()
        timeStop = chronometer.base - SystemClock.elapsedRealtime()
        chronometer.stop()
    }

    override fun onRestart() {
        super.onRestart()
        chronometer.base = SystemClock.elapsedRealtime() + timeStop
        chronometer.start()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        timeStop = chronometer.base - SystemClock.elapsedRealtime()
        chronometer.stop()

        showCloseDialog()
    }

    private fun showCloseDialog() {
        val customDialog = CustomDialog.Builder()
            .setImagen(R.drawable.ic_close_leccion)
            .setTitulo("Perderás tu avance")
            .setDescripcion("¿deseas terminar la lección?")
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
                chronometer.stop()
                finish()
            }

            override fun onCancelButtonClick() {
                customDialog.dismiss()
                chronometer.base = SystemClock.elapsedRealtime() + timeStop
                chronometer.start()
            }

            override fun onContinueButtonClick() {
            }

        })
    }
}
