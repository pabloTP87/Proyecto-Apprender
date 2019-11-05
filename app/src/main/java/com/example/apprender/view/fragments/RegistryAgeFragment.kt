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

class RegistryAgeFragment : Fragment() {

    private var iAgeSend: IAgeSend? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_registry_age, container, false)

        val txtAge = view.findViewById<EditText>(R.id.txtAge)
        val btnMic = view.findViewById<Button>(R.id.btnMicAge)
        val btnCheck = view.findViewById<Button>(R.id.btnCheckAge)

        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic, txtAge)

        val btnAudioQst3 = view.findViewById<Button>(R.id.btnQst3)
        val audioQst3 = MediaPlayer.create(this.context,R.raw.pregunta_3)

        btnAudioQst3.setOnClickListener {
            audioQst3.seekTo(0)
            audioQst3.start()
        }

        btnCheck.setOnClickListener {

            val edad = txtAge.text.toString()
            iAgeSend?.userAge(edad.toInt())

        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        iAgeSend = activity as IAgeSend
    }

    interface IAgeSend{
        fun userAge (edad: Int)
    }
}
