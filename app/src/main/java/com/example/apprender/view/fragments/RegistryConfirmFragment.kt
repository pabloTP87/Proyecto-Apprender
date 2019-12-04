package com.example.apprender.view.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
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
import com.example.apprender.view.MainActivity
import com.example.apprender.viewmodel.FirestoreViewModel
import com.google.firebase.Timestamp
import kotlinx.android.synthetic.main.fragment_registry_confirm.*

class RegistryConfirmFragment : Fragment() {

    private var viewModel = FirestoreViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(requireActivity()).get(FirestoreViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_registry_confirm, container, false)

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

        saveObserve()
        // Inflate the layout for this fragment
        return view
    }

    private fun saveObserve(){

        viewModel.fetchDataComplition().observe(this, Observer {
            if (it){
                user_save_charge.visibility = View.INVISIBLE
                val customDialog = CustomDialog.Builder()
                    .setImagen(R.drawable.ic_check_registry)
                    .setTitulo("Felicitaciones")
                    .setDescripcion("has terminado tu registro")
                    .setPositiveButtonText("Iniciar")
                    .build()

                customDialog.show(fragmentManager!!,"Custom dialog")
                customDialog.isCancelable = false

                customDialog.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
                    override fun onPositiveButtonClick() {
                        val intent = Intent(requireActivity(),MainActivity::class.java)
                        startActivity(intent)
                    }
                })
            }
        })
    }
}
