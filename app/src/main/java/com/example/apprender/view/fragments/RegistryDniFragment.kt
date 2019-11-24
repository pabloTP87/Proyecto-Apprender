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

class RegistryDniFragment : Fragment() {

    private lateinit var idatosUsuario : IDatosUsuario
    private var validator: Validator =
        Validator()
    private var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_dni, container, false)

        val mLayoutInflater = layoutInflater
        layout = R.id.registry_dni_layout

        val txtDni = view.findViewById<EditText>(R.id.txtRut)
        val btnMic = view.findViewById<Button>(R.id.btnMicDni)
        val btnCheck = view.findViewById<Button>(R.id.btnCheckRut)

        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic,txtDni)

        val btnAudioQst4 = view.findViewById<Button>(R.id.btnQst4)
        val audioQst4 = MediaPlayer.create(this.context,R.raw.pregunta_4)

        btnAudioQst4.setOnClickListener {
            audioQst4.seekTo(0)
            audioQst4.start()
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Se guarda el resultado de reemplazar un espacio por un caracter vacío
                val resultado = s.toString().replace(" ", "")
                // Evaluamos si el editText tiene o forma espacios entre los caracteres
                if (!s.toString().equals(resultado)) {
                    txtDni.setText(resultado) // asignamos el texto sin espacios
                    txtDni.setSelection(resultado.length) // asignamos el rango del string al editText
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val rut = txtDni.text.toString()

                if (rut.isNotEmpty()){
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryDniFragment.context!!,R.color.btn_green_selector_unpressed)
                    btnMic.backgroundTintList = ContextCompat.getColorStateList(context!!,R.color.btn_mic_primary_dark_selector_unpressed)
                }else {
                    btnCheck.isEnabled = false
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryDniFragment.context!!,R.color.btn_disable_grey)
                }
            }
        }

        txtDni.addTextChangedListener(textWatcher)

        btnCheck.setOnClickListener {

            val rut = txtDni.text.toString()

            val rutVal = validator.validarRut(rut)

            if (rutVal == "Rut invalido") {

                val aciertoRut = false

                validator.showSnackBar(this.context!!,aciertoRut,mLayoutInflater,view,layout)

            } else {

                idatosUsuario.rutUsuario(rut, rutVal)
            }
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        idatosUsuario = activity as IDatosUsuario
    }
}
