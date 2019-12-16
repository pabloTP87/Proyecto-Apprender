package com.example.apprender.view.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apprender.R
import com.example.apprender.logica.CustomDialog
import com.example.apprender.logica.Session
import com.example.apprender.view.MainActivity
import com.example.apprender.viewmodel.FirestoreViewModel
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_registry_confirm.*
import kotlinx.android.synthetic.main.fragment_registry_confirm.view.*

class RegistryConfirmFragment : Fragment() {

    private var viewModel = FirestoreViewModel()
    lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registry_confirm, container, false)

        viewModel = ViewModelProviders.of(requireActivity()).get(FirestoreViewModel::class.java)
        session = Session(requireContext())

        val audio = MediaPlayer.create(requireContext(),R.raw.datos_registro)

        val btnGuardar = view.findViewById<Button>(R.id.btnGuardar)
        val nameUser = view.findViewById<TextView>(R.id.txtFullName)
        val edadUser = view.findViewById<TextView>(R.id.txtAge)
        val rutUser = view.findViewById<TextView>(R.id.txtRut)
        val sexo = view.findViewById<TextView>(R.id.txtGenero)

        val nombre = arguments!!.getString("nombre")
        val apepat = arguments!!.getString("apepat")

        val name = "$nombre $apepat"
        nameUser.text = name

        val edad = arguments!!.getInt("edad")
        val edadString = edad.toString()
        edadUser.text = edadString

        val rut = arguments?.getString("rut")
        val rutDB = arguments!!.getString("dv")
        rutUser.text = rut

        val genero = arguments!!.getString("genero")
        sexo.text = genero

        btnGuardar.setOnClickListener {
            // Progress bar color
            user_save_charge.indeterminateDrawable.setColorFilter(Color.GRAY,PorterDuff.Mode.SRC_IN)
            // Show progress bar
            user_save_charge.visibility = View.VISIBLE
            // Firestore save user
            viewModel.crearUsuario(nombre!!,apepat!!,edad,rutDB!!.toInt(),genero!!, Timestamp(java.util.Date()))
        }

        view.btnAudioConfirm.setOnClickListener {
            audio.start()
        }

        saveObserve(nombre!!,rutDB!!)
        // Inflate the layout for this fragment
        return view
    }

    private fun saveObserve(nombre: String, rut: String){

        val audioFinRegistro = MediaPlayer.create(requireContext(),R.raw.fin_registro)

        viewModel.fetchDataComplition().observe(this, Observer {
            if (it){
                user_save_charge.visibility = View.INVISIBLE
                val customDialog = CustomDialog.Builder()
                    .setImagen(R.drawable.ic_check_registry)
                    .setTitulo("Felicitaciones")
                    .setDescripcion("has terminado tu registro")
                    .setContinueButtonVisible(true)
                    .setContinueButtonText("Iniciar")
                    .build()

                customDialog.show(fragmentManager!!,"Custom dialog")
                customDialog.isCancelable = false
                audioFinRegistro.start()

                customDialog.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
                    override fun onPositiveButtonClick() {
                    }

                    override fun onCancelButtonClick() {
                    }

                    override fun onContinueButtonClick() {
                        session.createLoginSession(nombre, rut)
                        // Inicializamos las lecciones gurdandolas con puntaje 0
                        viewModel.saveLeccionData("capitulo_1","leccion_1","Reconociendo las vocales",0,0,0,0,"enabled",rut)
                        viewModel.saveLeccionData("capitulo_1","leccion_2","Asociando imágenes",0,0,0,0,"disabled",rut)
                        viewModel.saveLeccionData("capitulo_1","leccion_3","Completa las vocales",0,0,0,0,"disabled",rut)
                        viewModel.saveLeccionData("capitulo_2","leccion_1","Separando las sílabas",0,0,0,0,"disabled",rut)
                        viewModel.saveLeccionData("capitulo_2","leccion_2","Marcando las sílabas",0,0,0,0,"disabled",rut)
                        viewModel.saveLeccionData("capitulo_2","leccion_3","Lee palabras",0,0,0,0,"disabled",rut)
                        viewModel.saveLeccionData("capitulo_2","leccion_4","Lee oraciones",0,0,0,0,"disabled",rut)
                        // Inicializamos la encuesta de satisfacción con datos vacios
                        viewModel.saveEncuestaData("","","",rut)

                        audioFinRegistro.stop()

                        val intent = Intent(requireContext(),MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(requireContext(),"Bienvenido $nombre", Toast.LENGTH_SHORT).show()
                        activity!!.finish()
                    }
                })
            }
        })
    }
}
