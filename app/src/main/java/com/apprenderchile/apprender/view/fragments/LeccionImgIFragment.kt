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

class LeccionImgIFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_leccion_img_i, container, false)

        val a = MediaPlayer.create(requireContext(),R.raw.a)
        val e = MediaPlayer.create(requireContext(),R.raw.e)
        val i = MediaPlayer.create(requireContext(),R.raw.i)
        val o = MediaPlayer.create(requireContext(),R.raw.o)
        val u = MediaPlayer.create(requireContext(),R.raw.u)

        layout = R.id.leccion_img_i_layout
        val mLayoutInflater = layoutInflater
        btnVerificar = view.findViewById(R.id.btnVerificar)
        rgOptions = view.findViewById(R.id.radioGroup)

        rgOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.a -> {
                    a.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionImgIFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.e -> {
                    e.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionImgIFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.i -> {
                    i.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionImgIFragment.context!!,R.color.btn_green_selector_unpressed)
                }

                R.id.o -> {
                    o.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionImgIFragment.context!!,R.color.btn_green_selector_unpressed)
                }
                R.id.u -> {
                    u.start()
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionImgIFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }

            btnVerificar.setOnClickListener {

                when (rgOptions.checkedRadioButtonId) {
                    R.id.a -> {
                        val puntaje = 0
                        val acierto = false

                        validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)

                        iVocalesOne.datosLeccionThree(puntaje,acierto)
                    }

                    R.id.e -> {
                        val puntaje = 0
                        val acierto = false

                        validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                        iVocalesOne.datosLeccionThree(puntaje,acierto)
                    }

                    R.id.i -> {
                        val puntaje = 5
                        val acierto = true

                        validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                        iVocalesOne.datosLeccionThree(puntaje,acierto)
                    }

                    R.id.o -> {
                        val puntaje = 0
                        val acierto = false

                        validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                        iVocalesOne.datosLeccionThree(puntaje,acierto)
                    }

                    R.id.u -> {
                        val puntaje = 0
                        val acierto = false

                        validator.showSnackBar(this.context!!,acierto,mLayoutInflater,view,layout)
                        iVocalesOne.datosLeccionThree(puntaje,acierto)
                    }
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
