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
import com.example.apprender.R
import com.example.apprender.logica.Recognition
import com.example.apprender.interfaces.IDatosUsuario
import com.example.apprender.logica.Validator

class RegistryNameFragment : Fragment() {

    private lateinit var idatosUsuario : IDatosUsuario
    private var validator: Validator = Validator()
    private var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_name, container, false)

        val mLayoutInflater = layoutInflater
        layout = R.id.registry_name_layout

        // Instanciar elementos de UI
        val btnMic = view.findViewById<Button>(R.id.btnMicRegistry)
        val btnCheck = view.findViewById<Button>(R.id.btnVerificar)
        val txtName = view.findViewById<EditText>(R.id.txtQst1)
        val btnAudioQst1 = view.findViewById<Button>(R.id.btnQst1)
        val audioQst1 = MediaPlayer.create(this.context,R.raw.pregunta_1)

        // Instanciar clase Recognition la cuál contiene el método para el reconocimiento de voz
        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic,txtName)

        btnAudioQst1.setOnClickListener {
            audioQst1.seekTo(0)
            audioQst1.start()
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val name = txtName.text.toString()

                if (name.isNotEmpty()){
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryNameFragment.context!!,R.color.btn_green_selector_unpressed)
                    btnMic.backgroundTintList = ContextCompat.getColorStateList(context!!,R.color.btn_mic_primary_dark_selector_unpressed)
                }else {
                    btnCheck.isEnabled = false
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryNameFragment.context!!,R.color.btn_disable_grey)
                }
            }

        }

        txtName.addTextChangedListener(textWatcher)

        btnCheck.setOnClickListener {

            val name = txtName.text.toString()

            if (matchInput(name)){

                idatosUsuario.nombreUsuario(name)

            }else {
                val aciertoNombre = false

                validator.showSnackBar(this.context!!,aciertoNombre,mLayoutInflater,view,layout)
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
        // matchentire valida la cadena de texto completa contra regex
        val matchPat = pat.matchEntire(input)

        // matchEntire retorna null si no hay match con regex -> la función devuelve siempre una cadena valida
        return (matchPat != null)
    }

}
