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
import com.example.apprender.R
import com.example.apprender.data.Recognition

class RegistryDniFragment : Fragment() {

    private var iRutSend: IRutSend? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_dni, container, false)

        val txtDni = view.findViewById<EditText>(R.id.txtRut)
        val btnMic = view.findViewById<FloatingActionButton>(R.id.btnMicDni)
        val btnCheck = view.findViewById<Button>(R.id.btnCheckRut)

        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic,txtDni)

        val btnAudioQst4 = view.findViewById<Button>(R.id.btnQst4)
        val audioQst4 = MediaPlayer.create(this.context,R.raw.pregunta_4)

        btnAudioQst4.setOnClickListener {
            audioQst4.seekTo(0)
            audioQst4.start()
        }

        btnCheck.setOnClickListener {

            val rut = txtDni.text.toString()
            iRutSend?.rutUsuario(rut)
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        iRutSend = activity as IRutSend
    }

    interface  IRutSend{
        fun rutUsuario(rut: String)
    }


}
