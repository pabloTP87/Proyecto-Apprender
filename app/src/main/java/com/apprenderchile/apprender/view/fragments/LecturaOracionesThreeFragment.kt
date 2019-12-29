package com.apprenderchile.apprender.view.fragments

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.interfaces.ILeccionVocalesOne
import com.apprenderchile.apprender.logica.Recognition
import com.apprenderchile.apprender.logica.Validator

class LecturaOracionesThreeFragment : Fragment() {

    private lateinit var iOracionesThree : ILeccionVocalesOne
    private var validator: Validator = Validator()
    private var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lectura_oraciones_three, container, false)

        layout = R.id.lectura_oraciones_three_layout
        val mLayoutInflater = layoutInflater

        val btnMic = view.findViewById<Button>(R.id.btn_mic_lect_ora_three)
        val btnCheck = view.findViewById<Button>(R.id.btnVerificar)
        val input = view.findViewById<EditText>(R.id.txt_lect_ora_three)

        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic,input)

        input.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = input.text.toString().trim()

                if (text.isNotEmpty()){
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@LecturaOracionesThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                    btnMic.backgroundTintList = ContextCompat.getColorStateList(this@LecturaOracionesThreeFragment.context!!,R.color.btn_mic_primary_dark_selector_unpressed)
                }else{
                    btnCheck.isEnabled = false
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@LecturaOracionesThreeFragment.context!!,R.color.btn_disable_grey)
                }
            }
        })

        btnCheck.setOnClickListener {

            val silabaOne = input.text.toString()

            if (matchInput(silabaOne)){

                if (silabaOne.equals("mi tía me da mote") || silabaOne.equals("mi tía me da Mote")){

                    val puntaje = 5
                    val acierto = true
                    iOracionesThree.datosLeccionThree(puntaje, acierto)
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                } else {

                    val acierto = false
                    val puntaje = 0
                    iOracionesThree.datosLeccionThree(puntaje, acierto)
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                }
            }else {

                val acierto = false
                val puntaje = 0
                iOracionesThree.datosLeccionThree(puntaje, acierto)
                validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iOracionesThree = activity as ILeccionVocalesOne
    }

    fun matchInput(input: CharSequence): Boolean{
        // expresión regular para aceptar solo texto
        val pat = Regex("[a-zA-ZñÑáéíóúÁÉÍÓÚ ]+")
        // matchentire valida la cadena de texto completa contra regex
        val matchPat = pat.matchEntire(input)

        // matchEntire retorna null si no hay match con regex -> la función devuelve siempre una cadena valida
        return (matchPat != null)
    }
}
