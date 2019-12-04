package com.example.apprender.view.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.apprender.R
import com.example.apprender.logica.Recognition
import com.example.apprender.interfaces.IDatosUsuario
import com.example.apprender.logica.Validator

class RegistryAgeFragment : Fragment() {

    private lateinit var idatosUsuario : IDatosUsuario
    private var validator: Validator =
        Validator()
    private var layout: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_registry_age, container, false)

        val mLayoutInflater = layoutInflater
        layout = R.id.registry_age_layout

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

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val age = txtAge.text.toString()

                if (age.isNotEmpty()){
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryAgeFragment.context!!,R.color.btn_green_selector_unpressed)
                    btnMic.backgroundTintList = ContextCompat.getColorStateList(context!!,R.color.btn_mic_primary_dark_selector_unpressed)
                }else {
                    btnCheck.isEnabled = false
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryAgeFragment.context!!,R.color.btn_disable_grey)
                }
            }

        }

        txtAge.addTextChangedListener(textWatcher)

        btnCheck.setOnClickListener {

            val edad = txtAge.text.toString()

            if (matchInput(edad)){

                idatosUsuario.userAge(edad.toInt())

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
        val pat = Regex("[0-9]{1,2}+")
        // matchEntire valida la cadena de texto completa contra regex
        val matchPat = pat.matchEntire(input)

        // matchEntire retorna null si no hay match con regex -> la función devuelve siempre una cadena valida
        return (matchPat != null)
    }
}
