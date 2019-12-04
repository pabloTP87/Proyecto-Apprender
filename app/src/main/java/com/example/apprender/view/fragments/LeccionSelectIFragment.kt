package com.example.apprender.view.fragments

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.apprender.interfaces.ILeccionVocalesOne
import com.example.apprender.R
import com.example.apprender.logica.Validator

class LeccionSelectIFragment : Fragment() {

    lateinit var iVocalesOne : ILeccionVocalesOne
    lateinit var btnVerificar : Button
    lateinit var rgOptions: RadioGroup

    var layout: Int = 0
    private var validator: Validator =
        Validator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leccion_select_i, container, false)
        layout = R.id.leccion_i_layout
        val mLayoutInflater = layoutInflater
        btnVerificar = view.findViewById(R.id.btnVerificar)
        rgOptions = view.findViewById(R.id.radioGroup)

        rgOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.arbol -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectIFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.iglesia -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectIFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.uña -> {
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectIFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnVerificar.setOnClickListener {

            when (rgOptions.checkedRadioButtonId) {
                R.id.arbol -> {
                    val puntaje = 0
                    val acierto = false
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iVocalesOne.datosLeccionThree(puntaje,acierto)
                }

                R.id.iglesia -> {
                    val puntaje = 5
                    val acierto = true
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iVocalesOne.datosLeccionThree(puntaje,acierto)
                }

                R.id.uña -> {
                    val puntaje = 0
                    val acierto = false
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iVocalesOne.datosLeccionThree(puntaje,acierto)
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
