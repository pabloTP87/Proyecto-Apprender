package com.apprenderchile.apprender.logica

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.view.RegistryActivity
import com.google.android.material.snackbar.Snackbar

class Validator {

    // Función para mostrar un snackbar en cada lección en la parte inferior, cambia de color si el acierto es correcto o incorrecto
    fun showSnackBar(context: Context, acierto: Boolean, inflater: LayoutInflater, view: View?,leccionLayout: Int){

        // creamos snackBar por defecto
        val bar = Snackbar.make(view!!.findViewById(leccionLayout),"",700)
        // obtenemos el snackBarLayout que permite personalizar este Item
        val barLayout = bar.view as Snackbar.SnackbarLayout
        // obtenemos el textView por defecto del snackBar y lo dejamos invisible
        val textBar = barLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textBar.visibility = View.INVISIBLE
        // variable para crear la vista inflando el layout personalizado para el snackBar
        val snackView = inflater.inflate(R.layout.correct_snackbar_layout, null) as View

        val correctText = "Respuesta correcta"
        val incorrectText = "Respuesta Incorrecta"
        val datoInvalido = "Dato invalido"

        // obtenemos los id de los elementos del layout personalizado, icono y texto
        val txtSnackBar = snackView.findViewById<TextView>(R.id.text_correct_toast)
        val imgSnackBar = snackView.findViewById<ImageView>(R.id.img_correct_toast)

        // si el acierto es correcto = true, asignamos los colores e icono correspondientes

        if (context is RegistryActivity){

            if (!acierto){

                txtSnackBar.text = datoInvalido
                imgSnackBar.setImageResource(R.drawable.ic_bad)
                snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.red_mic_pressed))
            }
        } else {

            if (acierto){

                txtSnackBar.text = correctText
                imgSnackBar.setImageResource(R.drawable.ic_good)
                snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.green_checked))

            } else {

                txtSnackBar.text = incorrectText
                imgSnackBar.setImageResource(R.drawable.ic_bad)
                snackView.setBackgroundColor(ContextCompat.getColor(context, R.color.red_mic_pressed))

            }
        }

        // Padding de los elementos dentro del snackBar
        barLayout.setPadding(0,0,0,0)
        // agregamos la vista personalizada al barLayout original
        barLayout.addView(snackView, 0)
        // mostramos el snackBar
        bar.show()
    }

    fun validarRut(rut: String): String {
        // Dígito verificador
        val dv: CharSequence
        // Expresión regular para validar rut con guión y DV
        val pat = Regex("^[0-9]+-[0-9k]$")
        // Validamos el rut completo como cadena de texto contra la expReg
        val match = pat.matchEntire(rut)
        // Si match no coincide con la expresión regular, devuelve Null
        if (match === null) {

            return "Rut invalido"
        }
        // Guardamos el dígito verificador cuando el rut sea de 9 o 10 dígitos
        when (rut.length) {
            9 -> {
                dv = rut.subSequence(8,9) // Separamos el DV y lo asignamos a la variable
            }

            10 -> {
                dv = rut.subSequence(9,10) // Separamos el DV y lo asignamos a la variable
            }

            else -> {
                return "Rut invalido"
            }
        }

        // Variable guion
        val guion = '-'
        // Separamos el rut desde el guion -> rutAll es un array donde en la posición 0 queda la mantisa del rut
        val rutAll = rut.split(guion)
        // Mantisa del rut
        val numRut = rutAll[0]
        // Validamos el dígito verificador ingresado contra la función validadora del DV. Si es true retornamos el rut validado
        if (dv != validarDv(numRut.toInt())){
            return "Rut invalido"
        }

        return numRut
    }

    fun validarDv (rut: Int): String {
        /* Esta función debe recibir la mantisa del rut (sin guion ni dígito verificador)
        *  Asi calculamos el dígito verificador corresponiente y devolvemos la validación
        * */
        var suma = 0 // suma para el algoritmo modulo 11
        var multiplicador = 1 // Multiplicador para el algoritmo modulo 11
        // Separamos cada caracter del rut y lo guardamos en un array
        val num = rut.toString().toCharArray()
        // Guardamos el rango del rut ingresado
        val rutRango = rut.toString().length -1
        // Recorremos cada dígito del rut
        for (i in rutRango downTo 0 ){ // equivalente a -> (int i = rut.ToString().Length - 1; i >= 0; i--)
            multiplicador ++
            // Según Modulo 11: cada dígito del rut se multiplica con los números del 2 al 7 respectivamente
            if (multiplicador == 8){
                multiplicador = 2
            }
            // guardamos en suma el resultante de la multiplicación
            suma += num[i].toString().toInt() * multiplicador
        }
        // Sacamos el restante de la division (Modulo %) de suma con el n° 11, lo restamos con 11 y nos da el digito verificador el rut
        val dv = 11 - (suma.rem(11))
        // Si DV es igual a 10 u 11 se remplaza con 0 o k, si no se devuelve en número resultante
        if (dv == 11) {

            return "0"

        } else if (dv == 10) {

            return "k"

        } else {

            return dv.toString()
        }
    }

    fun checkPermision(context: Context, activity: Activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            if(ContextCompat.checkSelfPermission(context,android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
                val permission = arrayOf(android.Manifest.permission.RECORD_AUDIO)
                ActivityCompat.requestPermissions(activity,permission,0)
            }
        }
    }
}