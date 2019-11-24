package com.example.apprender.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioGroup

import com.example.apprender.R
import com.example.apprender.interfaces.ISendEncuesta

class EncuestaOneFragment : Fragment() {

    private lateinit var iEnviarEncuesta : ISendEncuesta

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_encuesta_one, container, false)

        val btnAudio = view.findViewById<Button>(R.id.btn_audio_encuesta_one)
        val encuestaRB = view.findViewById<RadioGroup>(R.id.encuesta_group)
        val btnGuardar = view.findViewById<Button>(R.id.btn_guardar_encuesta)

        encuestaRB.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId){
                R.id.rb_in -> {
                    btnGuardar.isEnabled = true
                    btnGuardar.backgroundTintList = ContextCompat.getColorStateList(this@EncuestaOneFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.rb_sat -> {
                    btnGuardar.isEnabled = true
                    btnGuardar.backgroundTintList = ContextCompat.getColorStateList(this@EncuestaOneFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.rb_msat -> {
                    btnGuardar.isEnabled = true
                    btnGuardar.backgroundTintList = ContextCompat.getColorStateList(this@EncuestaOneFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnGuardar.setOnClickListener {
            val respuesta: String

            when (encuestaRB.checkedRadioButtonId) {

                R.id.rb_in -> {
                    respuesta = "Insatisfecho"
                    iEnviarEncuesta.enviarEncuestaOne(respuesta)
                }

                R.id.rb_sat -> {
                    respuesta = "Satisfecho"
                    iEnviarEncuesta.enviarEncuestaOne(respuesta)
                }

                R.id.rb_msat -> {
                    respuesta = "Muy satisfecho"
                    iEnviarEncuesta.enviarEncuestaOne(respuesta)
                }
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iEnviarEncuesta = activity as ISendEncuesta
    }

}
