package com.example.apprender.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup

import com.example.apprender.R
import com.example.apprender.interfaces.ILeccionVocalesOne
import com.example.apprender.logica.Validator

class MarSilabasThreeFragment : Fragment() {

    private lateinit var iSilabasTwo : ILeccionVocalesOne
    lateinit var btnVerificar : Button
    private lateinit var rgOptions: RadioGroup

    private var validator: Validator = Validator()
    private var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mar_silabas_three, container, false)

        layout = R.id.mar_silabas_three_layout
        val mLayoutInflater = layoutInflater

        btnVerificar = view.findViewById(R.id.btnVerificar)
        rgOptions = view.findViewById(R.id.radioGroup)

        rgOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.sil_1 -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@MarSilabasThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.sil_2 -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@MarSilabasThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.sil_3 -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@MarSilabasThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnVerificar.setOnClickListener {

            when (rgOptions.checkedRadioButtonId) {
                R.id.sil_1 -> {
                    val puntaje = 5
                    val acierto = true

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                    iSilabasTwo.datosLeccionThree(puntaje,acierto)
                }

                R.id.sil_2 -> {
                    val puntaje = 0
                    val acierto = false

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                    iSilabasTwo.datosLeccionThree(puntaje,acierto)
                }

                R.id.sil_3 -> {
                    val puntaje = 0
                    val acierto = false

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iSilabasTwo.datosLeccionThree(puntaje,acierto)
                }
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iSilabasTwo = activity as ILeccionVocalesOne
    }
}
