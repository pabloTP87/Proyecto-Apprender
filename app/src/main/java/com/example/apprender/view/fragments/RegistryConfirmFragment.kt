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
import kotlinx.android.synthetic.main.fragment_registry_confirm.*
import kotlinx.android.synthetic.main.fragment_registry_confirm.view.*
import kotlinx.android.synthetic.main.registry_confirm_dialog.view.*

class RegistryConfirmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_confirm, container, false)

        val btnAudioConfirm = view.findViewById<Button>(R.id.btnAudioConfirm)
        val audioConfirm = MediaPlayer.create(this.context,R.raw.fin_registro)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)
        val nameUser = view.findViewById<TextView>(R.id.txtFullName)
        val edadUser = view.findViewById<TextView>(R.id.txtAge)
        val rutUser = view.findViewById<TextView>(R.id.txtRut)
        val sexo = view.findViewById<TextView>(R.id.txtGenero)

        val nombre = arguments?.getString("nombre")
        val apepat = arguments?.getString("apepat")

        val name = "Nombre: $nombre $apepat"
        nameUser.text = name

        val edad = arguments?.getInt("edad")
        val edadString = "Edad: ${edad.toString()}"
        edadUser.text = edadString

        val rut = arguments?.getString("rut")
        val rutString = "Rut: $rut"
        rutUser.text = rutString

        val genero = arguments?.getString("genero")
        val generoString = "Genero: $genero"
        sexo.text = generoString


        btnAudioConfirm.setOnClickListener {
            audioConfirm.seekTo(0)
            audioConfirm.start()
        }

        btnGuardar.setOnClickListener {

            val confirmDialog = LayoutInflater.from(this.activity).inflate(R.layout.registry_confirm_dialog,null)
            val builder = AlertDialog.Builder(this.activity)
                .setView(confirmDialog)

            val alertDialog = builder.show()

            confirmDialog.btnStartMain.setOnClickListener {
                alertDialog.dismiss()

                val intent = Intent(this.activity,MainActivity::class.java)
                startActivity(intent)
            }
        }
        // Inflate the layout for this fragment
        return view
    }
}
