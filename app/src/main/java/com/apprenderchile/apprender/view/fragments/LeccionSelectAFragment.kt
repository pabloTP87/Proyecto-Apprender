package com.apprenderchile.apprender.view.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.interfaces.ILeccionVocalesOne
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.logica.Validator

class LeccionSelectAFragment : Fragment() {

    private lateinit var iVocalesOne : ILeccionVocalesOne
    lateinit var btnVerificar : Button
    private lateinit var rgOptions: RadioGroup

    var layout: Int = 0
    private var validator: Validator = Validator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leccion_select_a, container, false)

        val audio1 = MediaPlayer.create(requireContext(),R.raw.arbol)
        val audio2 = MediaPlayer.create(requireContext(),R.raw.ojo)
        val audio3 = MediaPlayer.create(requireContext(),R.raw.estrella)

        layout = R.id.leccion_a_layout
        val mLayoutInflater = layoutInflater

        btnVerificar = view.findViewById(R.id.btnVerificar)
        rgOptions = view.findViewById(R.id.radioGroup)

        rgOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.arbol -> {
                    audio1.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectAFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.ojo -> {
                    audio2.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectAFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.estrella -> {
                    audio3.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectAFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnVerificar.setOnClickListener {

            when (rgOptions.checkedRadioButtonId) {
                R.id.arbol -> {
                    val puntaje = 5
                    val acierto = true

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                    iVocalesOne.datosLeccionOne(puntaje,acierto)
                }

                R.id.ojo -> {
                    val puntaje = 0
                    val acierto = false

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iVocalesOne.datosLeccionOne(puntaje,acierto)
                }

                R.id.estrella -> {
                    val puntaje = 0
                    val acierto = false

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iVocalesOne.datosLeccionOne(puntaje,acierto)
                }
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iVocalesOne = activity as ILeccionVocalesOne
    }
}
