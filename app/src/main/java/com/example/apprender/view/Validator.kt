package com.example.apprender.view

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.apprender.R

class Validator {

    // Función para mostrar un snackbar en cada lección en la parte inferior, cambia de color si el acierto es correcto o incorrecto
    fun showSnackBar(context: Context, acierto: Boolean, inflater: LayoutInflater, view: View?,leccionLayout: Int){

        // creamos snackBar por defecto
        val bar = Snackbar.make(view!!.findViewById(leccionLayout),"",700)
        // obtenemos el snackBarLayout que permite personalizar este Item
        val barLayout = bar.view as Snackbar.SnackbarLayout
        // obtenemos el textView por defecto del snackBar y lo dejamos invisible
        val textBar = barLayout.findViewById<TextView>(android.support.design.R.id.snackbar_text)
        textBar.visibility = View.INVISIBLE
        // variable para crear la vista inflando el layout personalizado para el snackBar
        val snackView = inflater.inflate(R.layout.correct_snackbar_layout, null) as View

        val correctText = "Respuesta correcta"
        val incorrectText = "Respuesta Incorrecta"

        // obtenemos los id de los elementos del layout personalizado, icono y texto
        val txtSnackBar = snackView.findViewById<TextView>(R.id.text_correct_toast)
        val imgSnackBar = snackView.findViewById<ImageView>(R.id.img_correct_toast)
        // si el acierto es correcto = true, asignamos los colores e icono correspondientes
        if (acierto){

            txtSnackBar.text = correctText
            imgSnackBar.setImageResource(R.drawable.ic_good)
            snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.green_checked))

        } else {

            txtSnackBar.text = incorrectText
            imgSnackBar.setImageResource(R.drawable.ic_bad)
            snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.red_mic_pressed))

        }
        // Padding de los elementos dentro del snackBar
        barLayout.setPadding(0,0,0,0)
        // agregamos la vista personalizada al barLayout original
        barLayout.addView(snackView, 0)
        // mostramos el snackBar
        bar.show()

    }
}