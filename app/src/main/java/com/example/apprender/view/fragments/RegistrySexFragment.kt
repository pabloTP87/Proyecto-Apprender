package com.example.apprender.view.fragments


import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton

import com.example.apprender.R
import kotlinx.android.synthetic.main.fragment_registry_sex.*

class RegistrySexFragment : Fragment() {

    private var iGenderSend: IGenderSend? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_sex, container, false)

        val btnCheck = view.findViewById<Button>(R.id.btnCheckSex)
        val btnAudioQst5 = view.findViewById<Button>(R.id.btnQst5)
        val audioQst5 = MediaPlayer.create(this.context,R.raw.pregunta_5)

        val rbHombre = view.findViewById<RadioButton>(R.id.rbHombre)
        val rbMujer = view.findViewById<RadioButton>(R.id.rbMujer)
        val rbOtro = view.findViewById<RadioButton>(R.id.rbOtro)

        btnAudioQst5.setOnClickListener {
            audioQst5.seekTo(0)
            audioQst5.start()
        }



        btnCheck.setOnClickListener {

            val gender: String

            when (sexGroup.checkedRadioButtonId){

                R.id.rbHombre -> {
                    gender = "hombre"
                    iGenderSend?.generoUsuario(gender)
                }
                R.id.rbMujer -> {
                    gender = "Mujer"
                    iGenderSend?.generoUsuario(gender)
                }
                R.id.rbOtro -> {
                    gender = "otro"
                    iGenderSend?.generoUsuario(gender)
                }
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        iGenderSend = activity as IGenderSend
    }

    interface IGenderSend{
        fun generoUsuario(genero: String)
    }
}
