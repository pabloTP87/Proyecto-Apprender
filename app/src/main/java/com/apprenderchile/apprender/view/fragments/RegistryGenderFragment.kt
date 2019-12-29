package com.apprenderchile.apprender.view.fragments

import android.media.MediaPlayer
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.interfaces.IDatosUsuario

class RegistryGenderFragment : Fragment() {

    private lateinit var idatosUsuario : IDatosUsuario

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_gender, container, false)

        val btnCheck = view.findViewById<Button>(R.id.btnCheckSex)
        val btnAudioQst5 = view.findViewById<Button>(R.id.btnQst5)
        val genderGroup = view.findViewById<RadioGroup>(R.id.gender_group)
        val audioQst5 = MediaPlayer.create(this.context,R.raw.pregunta_5)

        btnAudioQst5.setOnClickListener {
            audioQst5.seekTo(0)
            audioQst5.start()
        }

        genderGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId){
                R.id.rbHombre -> {
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryGenderFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.rbMujer -> {
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryGenderFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.rbOtro -> {
                    btnCheck.isEnabled = true
                    btnCheck.backgroundTintList = ContextCompat.getColorStateList(this@RegistryGenderFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnCheck.setOnClickListener {

            val gender: String

            when (genderGroup.checkedRadioButtonId){

                R.id.rbHombre -> {
                    gender = "hombre"
                    idatosUsuario.generoUsuario(gender)
                }
                R.id.rbMujer -> {
                    gender = "Mujer"
                    idatosUsuario.generoUsuario(gender)
                }
                R.id.rbOtro -> {
                    gender = "otro"
                    idatosUsuario.generoUsuario(gender)
                }
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
