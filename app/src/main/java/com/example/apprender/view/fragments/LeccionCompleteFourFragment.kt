package com.example.apprender.view.fragments

import android.os.Bundle
import androidx.core.content.ContextCompat
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.apprender.interfaces.ILeccionVocalesOne
import com.example.apprender.R
import com.example.apprender.logica.Validator

class LeccionCompleteFourFragment : Fragment() {

    private lateinit var iVocalesOne : ILeccionVocalesOne
    lateinit var btnVerificar : Button

    var layout: Int = 0
    private var validator: Validator =
        Validator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leccion_complete_four, container, false)
        layout = R.id.leccion_complete_four_layout
        val mLayoutInflater = layoutInflater
        btnVerificar = view.findViewById(R.id.btnVerificar)
        val rg = view.findViewById<RadioGroup>(R.id.rg_vocals)

        val inputU = view.findViewById<EditText>(R.id.in_vocal_1)
        val inputA = view.findViewById<EditText>(R.id.in_vocal_2)

        inputU.inputType = InputType.TYPE_NULL
        inputA.inputType = InputType.TYPE_NULL

        rg.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){

                R.id.rb_1 -> {

                    if(inputU.isFocused) {

                        inputU.setText("a")
                    }
                    else if (inputA.isFocused){

                        inputA.setText("a")
                    }
                }

                R.id.rb_2 -> {

                    if(inputU.isFocused) {

                        inputU.setText("e")
                    }
                    if (inputA.isFocused){

                        inputA.setText("e")
                    }
                }

                R.id.rb_3 -> {

                    if(inputU.isFocused) {

                        inputU.setText("i")
                    }
                    if (inputA.isFocused){

                        inputA.setText("i")
                    }
                }

                R.id.rb_4 -> {

                    if(inputU.isFocused) {

                        inputU.setText("o")
                    }
                    if (inputA.isFocused){

                        inputA.setText("o")
                    }
                }

                R.id.rb_5 -> {

                    if(inputU.isFocused) {

                        inputU.setText("u")
                    }
                    if (inputA.isFocused){

                        inputA.setText("u")
                    }
                }

            }
        }

        val textWatcher = object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (inputU.text.isNotEmpty() && inputA.text.isNotEmpty()){
                    btnVerificar.isEnabled = true
                    btnVerificar.backgroundTintList = ContextCompat.getColorStateList(this@LeccionCompleteFourFragment.context!!,R.color.btn_green_selector_unpressed)
                }
            }
        }

        inputU.addTextChangedListener(textWatcher)
        inputA.addTextChangedListener(textWatcher)

        btnVerificar.setOnClickListener {

            val vocalOne = inputU.text.toString()
            val vocalTwo = inputA.text.toString()

            if (vocalOne.equals("a") && vocalTwo.equals("o")) {

                val puntaje = 5
                val acierto = true

                validator.showSnackBar(this.context!!, acierto, mLayoutInflater, view, layout)

                iVocalesOne.datosLeccionFour(puntaje, acierto)

            } else {

                val puntaje = 0
                val acierto = false

                validator.showSnackBar(this.context!!, acierto, mLayoutInflater, view, layout)

                iVocalesOne.datosLeccionFour(puntaje, acierto)
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
