package com.example.apprender.view.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup

import com.example.apprender.R

class LeccionCompleteTwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_leccion_complete_one, container, false)

        val rg = view.findViewById<RadioGroup>(R.id.rg_vocals)
        val inputU = view.findViewById<EditText>(R.id.in_vocal_1)
        val inputA = view.findViewById<EditText>(R.id.in_vocal_2)

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
        // Inflate the layout for this fragment
        return view
    }


}
