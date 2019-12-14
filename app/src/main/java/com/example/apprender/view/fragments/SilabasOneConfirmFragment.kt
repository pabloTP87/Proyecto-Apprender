package com.example.apprender.view.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apprender.R
import com.example.apprender.logica.CustomDialog
import com.example.apprender.logica.LeccionStat
import com.example.apprender.logica.Session
import com.example.apprender.view.ChapterTwoActivity
import com.example.apprender.view.LeccionVocalesOneActivity
import com.example.apprender.view.MainActivity
import com.example.apprender.viewmodel.FirestoreViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_silabas_one_confirm.*

class SilabasOneConfirmFragment : Fragment() {

    private lateinit var iStopTimer : sendTimeChronometer

    private var estadistica: LeccionStat = LeccionStat()
    private var listaPuntaje: ArrayList<Int> = ArrayList()
    private var listaAciertos: ArrayList<Boolean> = ArrayList()

    private lateinit var txtTiempo: TextView

    lateinit var session: Session
    private lateinit var viewModel: FirestoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        session = Session(requireContext())
        viewModel = ViewModelProviders.of(requireActivity()).get(FirestoreViewModel::class.java)

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

            val userData = session.getUserData()
            val rut = userData[Session.KEY_RUT]
            val time = arguments!!.getLong("tiempo")
            val capitulo = "capitulo_2"
            val leccion = "leccion_1"

            if (leccionIncorrecta.toInt() >= 2){
                val customDialog = CustomDialog.Builder()
                    .setImagen(R.drawable.ic_close_leccion)
                    .setTitulo("No superaste la lección")
                    .setDescripcion("¿quieres volver a intentarlo?")
                    .setContinueButtonVisible(false)
                    .setPositiveButtonText("Si")
                    .setCancelButtonText("No")
                    .build()

                customDialog.show(fragmentManager!!, "custom dialog")
                customDialog.isCancelable = false

                customDialog.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
                    override fun onPositiveButtonClick() {
                        // Volvemos a mostrar la leccion
                        val intent = Intent(requireContext(), LeccionVocalesOneActivity::class.java)
                        startActivity(intent)
                        activity!!.finish()
                    }

                    override fun onCancelButtonClick() {
                        // guardamos los datos de la leccion no superada con estado enabled = aun disponible
                        viewModel.saveLeccionData(capitulo,leccion,puntaje.toInt(),time.toInt(),leccionCorrecta.toInt(),
                            leccionIncorrecta.toInt(),"enabled", rut!!)

                        val intent = Intent(requireContext(), MainActivity::class.java)
                        startActivity(intent)
                        activity!!.finish()

                    }

                    override fun onContinueButtonClick() {
                        // No esta disponible en este custom dialog
                    }
                })
            }else{
                // Guardamos datos de la lección superada
                leccion_save_charge.indeterminateDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
                leccion_save_charge.visibility = View.VISIBLE
                viewModel.saveLeccionData(capitulo,leccion,puntaje.toInt(),time.toInt(),leccionCorrecta.toInt(),
                    leccionIncorrecta.toInt(),"success", rut!!)
                // Actualizamos estado de leccion siguiente a enabled = habilitada
                viewModel.actualizarEstadoLeccion(rut,capitulo,"leccion_2","enabled")
            }
        }

        observeLeccionDataComplete()

        // Inflate the layout for this fragment
        return view
    }

    private fun observeLeccionDataComplete(){

        viewModel.fetchDataComplition().observe(this, Observer {
            if (it){
                leccion_save_charge.visibility = View.INVISIBLE
                val customDialog = CustomDialog.Builder()
                    .setImagen(R.drawable.ic_check_registry)
                    .setTitulo("Fin de la lección")
                    .setDescripcion("continua aprendiendo")
                    .setContinueButtonVisible(true)
                    .setContinueButtonText("Continuar")
                    .build()

                customDialog.show(fragmentManager!!, "custom dialog")
                customDialog.isCancelable = true

                customDialog.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
                    override fun onPositiveButtonClick() {
                        // No esta disponible en este custom dialog
                    }

                    override fun onCancelButtonClick() {
                        // No esta disponible en este custom dialog
                    }

                    override fun onContinueButtonClick() {
                        // No esta disponible en este custom dialog
                        val intent = Intent(requireContext(), ChapterTwoActivity::class.java)
                        startActivity(intent)
                        activity!!.finish()
                    }
                })
            }
        })
    }

    interface sendTimeChronometer{
        fun stopTimer()
    }

    override fun onAttach(context: Context) {
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
