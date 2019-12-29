package com.apprenderchile.apprender.view.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.interfaces.ISendEncuesta

class EncuestaThreeFragment : Fragment() {

    private lateinit var iEnviarEncuesta : ISendEncuesta

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_encuesta_three, container, false)

        val qst3 = MediaPlayer.create(requireContext(),R.raw.qst3_encuesta)
        val btnAudio = view.findViewById<Button>(R.id.btn_audio_encuesta_three)
        val encuestaRB = view.findViewById<RadioGroup>(R.id.encuesta_group)
        val btnGuardar = view.findViewById<Button>(R.id.btn_guardar_encuesta)

        btnAudio.setOnClickListener {
            qst3.start()
        }

        encuestaRB.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId){
                R.id.rb_in -> {
                    btnGuardar.isEnabled = true
                    btnGuardar.backgroundTintList = ContextCompat.getColorStateList(this@EncuestaThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.rb_sat -> {
                    btnGuardar.isEnabled = true
                    btnGuardar.backgroundTintList = ContextCompat.getColorStateList(this@EncuestaThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.rb_msat -> {
                    btnGuardar.isEnabled = true
                    btnGuardar.backgroundTintList = ContextCompat.getColorStateList(this@EncuestaThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnGuardar.setOnClickListener {
            val respuesta: String

            when (encuestaRB.checkedRadioButtonId) {

                R.id.rb_in -> {
                    respuesta = "Insatisfecho"
                    iEnviarEncuesta.enviarEncuestaThree(respuesta)
                }

                R.id.rb_sat -> {
                    respuesta = "Satisfecho"
                    iEnviarEncuesta.enviarEncuestaThree(respuesta)
                }

                R.id.rb_msat -> {
                    respuesta = "Muy satisfecho"
                    iEnviarEncuesta.enviarEncuestaThree(respuesta)
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
