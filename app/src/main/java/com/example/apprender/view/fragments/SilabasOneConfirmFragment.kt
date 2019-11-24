package com.example.apprender.view.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.apprender.R
import com.example.apprender.logica.LeccionStat
import com.example.apprender.view.ChapterTwoActivity
import kotlinx.android.synthetic.main.leccion_confirm_dialog.view.*

class SilabasOneConfirmFragment : Fragment() {

    private lateinit var iStopTimer : sendTimeChronometer

    private var estadistica: LeccionStat = LeccionStat()
    private var listaPuntaje: ArrayList<Int> = ArrayList()
    private var listaAciertos: ArrayList<Boolean> = ArrayList()

    private lateinit var txtTiempo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_silabas_one_confirm, container, false)

        val txtPuntaje = view.findViewById<TextView>(R.id.txtPuntaje)
        txtTiempo = view.findViewById(R.id.txtTiempo)
        val txtCorrectas = view.findViewById<TextView>(R.id.txtCorrectas)
        val txtIncorrectas = view.findViewById<TextView>(R.id.txtIncorrectas)
        val btnGuardar = view.findViewById<Button>(R.id.btn_guardar)

        listaPuntaje.add(arguments!!.getInt("puntaje_one"))
        listaPuntaje.add(arguments!!.getInt("puntaje_two"))
        listaPuntaje.add(arguments!!.getInt("puntaje_three"))

        listaAciertos.add(arguments!!.getBoolean("acierto_one"))
        listaAciertos.add(arguments!!.getBoolean("acierto_two"))
        listaAciertos.add(arguments!!.getBoolean("acierto_three"))

        val puntaje = estadistica.puntajeTotal(listaPuntaje).toString()
        txtPuntaje.text = puntaje

        val leccionCorrecta = estadistica.cantidadCorrectas(listaAciertos).toString()
        txtCorrectas.text = leccionCorrecta

        val leccionIncorrecta = estadistica.cantidadIncorrectas(listaAciertos).toString()
        txtIncorrectas.text = leccionIncorrecta

        btnGuardar.setOnClickListener {

            val confirmDialog = LayoutInflater.from(this.activity).inflate(R.layout.leccion_confirm_dialog,null)
            val builder = AlertDialog.Builder(this.activity).setView(confirmDialog)

            val alertDialog = builder.show()

            confirmDialog.btn_continuar.setOnClickListener {
                alertDialog.dismiss()

                val intent = Intent(this.activity, ChapterTwoActivity::class.java)
                startActivity(intent)
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    interface sendTimeChronometer{
        fun stopTimer()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        iStopTimer = activity as sendTimeChronometer
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        iStopTimer.stopTimer()
        val leccionTime = arguments!!.getLong("tiempo")
        txtTiempo.text = leccionTime.toString()
    }
}
