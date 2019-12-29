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
import com.apprenderchile.apprender.interfaces.ILeccionVocalesOne
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.logica.Validator

class LeccionSelectEFragment : Fragment() {

    private lateinit var iVocalesOne : ILeccionVocalesOne
    lateinit var btnVerificar : Button
    private lateinit var rgOptions: RadioGroup

    var layout: Int = 0
    private var validator: Validator =
        Validator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leccion_select_e, container, false)

        val audio1 = MediaPlayer.create(requireContext(),R.raw.escoba)
        val audio2 = MediaPlayer.create(requireContext(),R.raw.olla)
        val audio3 = MediaPlayer.create(requireContext(),R.raw.una)

        layout = R.id.leccion_e_layout
        val mLayoutInflater = layoutInflater
        btnVerificar = view.findViewById(R.id.btnVerificar)
        rgOptions = view.findViewById(R.id.radioGroup)

        rgOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.escoba -> {
                    audio1.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectEFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.olla -> {
                    audio2.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectEFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.uña -> {
                    audio3.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionSelectEFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        btnVerificar.setOnClickListener {

            when (rgOptions.checkedRadioButtonId) {
                R.id.escoba -> {
                    val puntaje = 5
                    val acierto = true
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                    iVocalesOne.datosLeccionTwo(puntaje,acierto)
                }

                R.id.olla -> {
                    val puntaje = 0
                    val acierto = false
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                    iVocalesOne.datosLeccionTwo(puntaje,acierto)
                }

                R.id.uña -> {
                    val puntaje = 0
                    val acierto = false
                    validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                    iVocalesOne.datosLeccionTwo(puntaje,acierto)
                }
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iVocalesOne = activity as ILeccionVocalesOne
    }
}
