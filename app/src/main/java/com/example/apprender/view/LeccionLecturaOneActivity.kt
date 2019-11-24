package com.example.apprender.view

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.widget.Chronometer
import android.widget.ImageButton
import com.example.apprender.R
import com.example.apprender.interfaces.ILeccionVocalesOne
import com.example.apprender.logica.Validator
import com.example.apprender.view.fragments.*
import kotlinx.android.synthetic.main.leccion_close_dialog.view.*

class LeccionLecturaOneActivity : AppCompatActivity(), ILeccionVocalesOne, LecturaOneConfirmFragment.sendTimeChronometer {

    private var manager = supportFragmentManager
    private lateinit var chronometer : Chronometer
    private var timeStop : Long = 0
    var bundle = Bundle(5)

    override fun stopTimer() {
        val fragment = LecturaOneConfirmFragment()
        chronometer.stop()
        val timer = (SystemClock.elapsedRealtime() - chronometer.base) / 1000

        bundle.putLong("tiempo", timer)
        fragment.arguments = bundle
    }

    override fun datosLeccionOne(puntaje: Int, acierto: Boolean) {
        val fragment = LecturaPalabrasTwoFragment()
        bundle.putInt("puntaje_one", puntaje)
        bundle.putBoolean("acierto_one", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionTwo(puntaje: Int, acierto: Boolean) {
        val fragment = LecturaPalabrasThreeFragment()
        bundle.putInt("puntaje_two", puntaje)
        bundle.putBoolean("acierto_two", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionThree(puntaje: Int, acierto: Boolean) {
        val fragment = LecturaOneConfirmFragment()
        bundle.putInt("puntaje_three", puntaje)
        bundle.putBoolean("acierto_three", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionFour(puntaje: Int, acierto: Boolean) {
    }

    override fun datosLeccionFive(puntaje: Int, acierto: Boolean) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion_lectura_one)

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
        val fragment = LecturaPalabrasOneFragment()
        transaction.replace(R.id.leccion_lctr_one_container,fragment)
        transaction.commit()
    }

    private fun createFragment(fragment: Fragment){

        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left)
        transaction.replace(R.id.leccion_lctr_one_container,fragment)
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
        val confirmDialog = LayoutInflater.from(this).inflate(R.layout.leccion_close_dialog,null)
        val builder = AlertDialog.Builder(this).setView(confirmDialog)

        val alertDialog = builder.show()

        alertDialog.setCanceledOnTouchOutside(false)

        confirmDialog.btn_si.setOnClickListener {
            alertDialog.dismiss()
            chronometer.stop()
            this.finish()
        }

        confirmDialog.btn_no.setOnClickListener {
            alertDialog.dismiss()
            chronometer.base = SystemClock.elapsedRealtime() + timeStop
            chronometer.start()
        }
    }
}
