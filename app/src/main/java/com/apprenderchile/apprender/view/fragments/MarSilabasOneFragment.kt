package com.apprenderchile.apprender.view.fragments

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.interfaces.ILeccionVocalesOne
import com.apprenderchile.apprender.logica.Validator

class MarSilabasOneFragment : Fragment() {

    private lateinit var iSilabasTwo : ILeccionVocalesOne
    lateinit var btnVerificar : Button
    private lateinit var rgOptions: RadioGroup

    private var validator: Validator = Validator()
    private var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mar_silabas_one, container, false)

        layout = R.id.mar_silabas_one_layout
        val mLayoutInflater = layoutInflater

        btnVerificar = view.findViewById(R.id.btnVerificar)
        rgOptions = view.findViewById(R.id.radioGroup)

        rgOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.sil_1 -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@MarSilabasOneFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.sil_2 -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@MarSilabasOneFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.sil_3 -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@MarSilabasOneFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnVerificar.setOnClickListener {

            when (rgOptions.checkedRadioButtonId) {
                R.id.sil_1 -> {
                    val puntaje = 0
                    val acierto = false

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iSilabasTwo.datosLeccionOne(puntaje,acierto)
                }

                R.id.sil_2 -> {
                    val puntaje = 5
                    val acierto = true

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                    iSilabasTwo.datosLeccionOne(puntaje,acierto)
                }

                R.id.sil_3 -> {
                    val puntaje = 0
                    val acierto = false

                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iSilabasTwo.datosLeccionOne(puntaje,acierto)
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
