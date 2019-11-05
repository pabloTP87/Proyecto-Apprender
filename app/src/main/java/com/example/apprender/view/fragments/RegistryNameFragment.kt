package com.example.apprender.view.fragments

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.apprender.R
import com.example.apprender.data.Recognition

class RegistryNameFragment : Fragment() {

    private var idatosUsuario : IdatosUsuario? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_name, container, false)

        // Instanciar elementos de UI
        val btnMic = view.findViewById<Button>(R.id.btnMicRegistry)
        val btnCheck = view.findViewById<Button>(R.id.btnVerificar)
        val txtName = view.findViewById<EditText>(R.id.txtQst1)
        val valName = view.findViewById<TextView>(R.id.validation)
        val btnAudioQst1 = view.findViewById<Button>(R.id.btnQst1)
        val audioQst1 = MediaPlayer.create(this.context,R.raw.pregunta_1)

        // Instanciar clase Recognition la cuál contiene el método para el reconocimiento de voz
        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic,txtName)

        btnAudioQst1.setOnClickListener {
            audioQst1.seekTo(0)
            audioQst1.start()
        }

        btnCheck.setOnClickListener {

            val name = txtName.text.toString()
            idatosUsuario?.nombreUsuario(name)

        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        idatosUsuario = activity as IdatosUsuario
    }

    interface IdatosUsuario{
        fun nombreUsuario(nombre: String)
    }
}
