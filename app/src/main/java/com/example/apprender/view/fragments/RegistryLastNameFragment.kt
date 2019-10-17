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

class RegistryLastNameFragment : Fragment() {

    private var ilastNameSend: ILastNameSend? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_registry_last_name, container, false)

        val txtLastName = view.findViewById<EditText>(R.id.txtLastName)
        val btnMic = view.findViewById<FloatingActionButton>(R.id.btnMicLastName)
        val btnCheck = view.findViewById<Button>(R.id.btnCheckLastName)

        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic, txtLastName)

        val btnAudioQst2 = view.findViewById<Button>(R.id.btnQst2)
        val audioQst2 = MediaPlayer.create(this.context,R.raw.pregunta_2)

        btnAudioQst2.setOnClickListener {
            audioQst2.seekTo(0)
            audioQst2.start()
        }

        btnCheck.setOnClickListener {

            val lastName = txtLastName.text.toString()
            ilastNameSend?.lastName(lastName)
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        ilastNameSend = activity as ILastNameSend
    }

    interface ILastNameSend{
        fun lastName(lastName: String)
    }


}
