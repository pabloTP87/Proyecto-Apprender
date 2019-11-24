package com.example.apprender.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.example.apprender.R
import com.example.apprender.view.MainActivity
import kotlinx.android.synthetic.main.encuesta_confirm_dialog.view.*
import kotlinx.android.synthetic.main.registry_confirm_dialog.view.*

class EncuestaConfirmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_encuesta_confirm, container, false)

        val btnAudioConfirm = view.findViewById<Button>(R.id.btn_audio_confirm_encuesta)
        val btnGuardar = view.findViewById<Button>(R.id.btn_Finalizar)

        val rspTxtOne = view.findViewById<TextView>(R.id.respuesta_1)
        val rspTxtTwo = view.findViewById<TextView>(R.id.respuesta_2)
        val rspTxtThree = view.findViewById<TextView>(R.id.respuesta_3)

        val respuestaOne = arguments?.getString("respuesta_one")
        val respuestaTwo = arguments?.getString("respuesta_two")
        val respuestaThree = arguments?.getString("respuesta_three")

        rspTxtOne.text = respuestaOne
        rspTxtTwo.text = respuestaTwo
        rspTxtThree.text = respuestaThree

        btnGuardar.setOnClickListener {

            val confirmDialog = LayoutInflater.from(this.context).inflate(R.layout.encuesta_confirm_dialog,null)
            val builder = AlertDialog.Builder(this.activity)
                .setView(confirmDialog)

            val alertDialog = builder.show()

            confirmDialog.btn_continuar.setOnClickListener {
                alertDialog.dismiss()

                val intent = Intent(this.activity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        // Inflate the layout for this fragment
        return view
    }


}
