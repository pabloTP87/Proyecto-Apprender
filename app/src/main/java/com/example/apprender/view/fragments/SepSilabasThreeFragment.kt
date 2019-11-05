package com.example.apprender.view.fragments


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
import com.example.apprender.data.Recognition

/**
 * A simple [Fragment] subclass.
 */
class SepSilabasThreeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sep_silabas_three, container, false)

        val btnMic = view.findViewById<Button>(R.id.btn_mic_sil3)
        val btnVerificar = view.findViewById<Button>(R.id.btnVerificar)
        val input = view.findViewById<EditText>(R.id.txt_sil_three)

        val recognition = Recognition(this.context)
        recognition.startSpeechToText(btnMic,input)

        input.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = input.text.toString().trim()

                if (text.isNotEmpty()){
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@SepSilabasThreeFragment.context!!,R.color.btn_green_selector_unpressed)
                    btnMic.backgroundTintList = ContextCompat.getColorStateList(this@SepSilabasThreeFragment.context!!,R.color.btn_mic_primary_dark_selector_unpressed)
                }else{
                    btnVerificar.isEnabled = false
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@SepSilabasThreeFragment.context!!,R.color.btn_disable_grey)
                }
            }

        })

        // Inflate the layout for this fragment
        return view
    }


}
