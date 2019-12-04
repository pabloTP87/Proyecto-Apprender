package com.example.apprender.view

import android.app.AlertDialog
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apprender.interfaces.ILeccionVocalesOne
import com.example.apprender.R
import com.example.apprender.view.fragments.*
import kotlinx.android.synthetic.main.leccion_close_dialog.view.*

class LeccionVocalesTwoActivity : AppCompatActivity(),
    ILeccionVocalesOne, VocalesTwoConfirmFragment.sendTimeChronometer{

    private lateinit var chronometer: Chronometer
    private var timeStop: Long = 0

    private val manager = supportFragmentManager
    var bundle = Bundle(15)

    override fun stopTimer() {
        val fragment = VocalesTwoConfirmFragment()
        chronometer.stop()
        val timer = (SystemClock.elapsedRealtime() - chronometer.base) / 1000

        bundle.putLong("tiempo", timer)
        fragment.arguments = bundle
    }

    override fun datosLeccionOne(puntaje: Int, acierto: Boolean) {
        val fragment = LeccionImgIFragment()
        bundle.putInt("puntaje_a", puntaje)
        bundle.putBoolean("acierto_a", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionTwo(puntaje: Int, acierto: Boolean) {
        val fragment = LeccionImgAFragment()
        bundle.putInt("puntaje_e", puntaje)
        bundle.putBoolean("acierto_e", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionThree(puntaje: Int, acierto: Boolean) {
        val fragment = LeccionImgUFragment()
        bundle.putInt("puntaje_i", puntaje)
        bundle.putBoolean("acierto_i", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun datosLeccionFour(puntaje: Int, acierto: Boolean) {
        val fragment = VocalesTwoConfirmFragment()
        bundle.putInt("puntaje_o", puntaje)
        bundle.putBoolean("acierto_o", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }
    override fun datosLeccionFive(puntaje: Int, acierto: Boolean) {
        val fragment = LeccionImgOFragment()
        bundle.putInt("puntaje_u", puntaje)
        bundle.putBoolean("acierto_u", acierto)
        fragment.arguments = bundle
        createFragment(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leccion_vocales_two)

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

    private fun createInitFragment() {

        val transaction = manager.beginTransaction()
        val fragment = LeccionImgEFragment()
        transaction.replace(R.id.leccion_vcls_two_container, fragment)
        transaction.commit()
    }

    private fun createFragment(fragment: Fragment) {

        val transaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
        transaction.replace(R.id.leccion_vcls_two_container, fragment)
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
        val confirmDialog =
            LayoutInflater.from(this).inflate(R.layout.leccion_close_dialog, null)
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
