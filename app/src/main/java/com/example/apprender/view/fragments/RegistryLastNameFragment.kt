package com.example.apprender.view.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.apprender.R
import com.example.apprender.data.Recognition
import com.example.apprender.view.IDatosUsuario
import com.example.apprender.view.Validator

class RegistryLastNameFragment : Fragment() {

    private lateinit var idatosUsuario : IDatosUsuario
    private var validator: Validator = Validator()
    private var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_registry_last_name, container, false)

        val mLayoutInflater = layoutInflater
        layout = R.id.registry_lastName_layout

        val txtLastName = view.findViewById<EditText>(R.id.txtLastName)
        val btnMic = view.findViewById<Button>(R.id.btnMicLastName)
        val btnCheck = view.findViewById<Button>(R.id.btnCheckLastName)
        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic, txtLastName)

        val btnAudioQst2 = view.findViewById<Button>(R.id.btnQst2)
        val audioQst2 = MediaPlayer.create(this.context,R.raw.pregunta_2)

        btnAudioQst2.setOnClickListener {
            audioQst2.seekTo(0)
            audioQst2.start()
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val lastName = txtLastName.text.toString()

                if (lastName.isNotEmpty()){
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryLastNameFragment.context!!,R.color.btn_green_selector_unpressed)
                    btnMic.backgroundTintList = ContextCompat.getColorStateList(context!!,R.color.btn_mic_primary_dark_selector_unpressed)
                }else {
                    btnCheck.isEnabled = false
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryLastNameFragment.context!!,R.color.btn_disable_grey)
                }
            }

        }

        txtLastName.addTextChangedListener(textWatcher)

        btnCheck.setOnClickListener {

            val lastName = txtLastName.text.toString()

            if (matchInput(lastName)){

                idatosUsuario.lastName(lastName)

            }else {

                val aciertoLastName = false

                validator.showSnackBar(this.context!!,aciertoLastName,mLayoutInflater,view,layout)
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        idatosUsuario = activity as IDatosUsuario
    }

    fun matchInput(input: CharSequence): Boolean{
        // expresión regular para aceptar solo texto
        val pat = Regex("[a-zA-ZñÑáéíóúÁÉÍÓÚ]+")
        // matchEntire valida la cadena de texto completa contra regex
        val matchPat = pat.matchEntire(input)

        // matchEntire retorna null si no hay match con regex -> la función devuelve siempre una cadena valida
        return (matchPat != null)
    }
}
