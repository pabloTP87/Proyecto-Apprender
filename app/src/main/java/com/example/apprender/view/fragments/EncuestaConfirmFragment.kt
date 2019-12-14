package com.example.apprender.view.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.apprender.R
import com.example.apprender.logica.CustomDialog
import com.example.apprender.logica.Session
import com.example.apprender.view.MainActivity
import com.example.apprender.viewmodel.FirestoreViewModel
import kotlinx.android.synthetic.main.fragment_encuesta_confirm.*

class EncuestaConfirmFragment : Fragment() {

    lateinit var session: Session
    lateinit var viewModel: FirestoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        session = Session(requireContext())
        viewModel = ViewModelProviders.of(requireActivity())[FirestoreViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_encuesta_confirm, container, false)

        val btnAudioConfirm = view.findViewById<Button>(R.id.btn_audio_confirm_encuesta)
        val btnGuardar = view.findViewById<Button>(R.id.btn_Finalizar)

        val respuestaOne = arguments?.getString("respuesta_one")
        val respuestaTwo = arguments?.getString("respuesta_two")
        val respuestaThree = arguments?.getString("respuesta_three")

        btnGuardar.setOnClickListener {
            val userData = session.getUserData()
            val rut = userData[Session.KEY_RUT]

            encuesta_save_charge.indeterminateDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
            encuesta_save_charge.visibility = View.VISIBLE
            viewModel.saveEncuestaData(respuestaOne!!,respuestaTwo!!,respuestaThree!!,rut!!)
        }

        observeSaveEncuesta()

        // Inflate the layout for this fragment
        return view
    }

    private fun observeSaveEncuesta(){

        viewModel.fetchDataComplition().observe(this, Observer {
            if (it){
                encuesta_save_charge.visibility = View.INVISIBLE
                val customDialog = CustomDialog.Builder()
                    .setImagen(R.drawable.ic_check_registry)
                    .setTitulo("Encuesta enviada")
                    .setDescripcion("Muchas gracias")
                    .setContinueButtonVisible(true)
                    .setContinueButtonText("Finalizar")
                    .build()

                customDialog.isCancelable = false
                customDialog.show(fragmentManager!!, "custom dialog")

                customDialog.setDialogButtonClickListener(object : CustomDialog.DialogButtonClickListener{
                    override fun onPositiveButtonClick() {
                        // No se Utiliza en este dialog
                    }

                    override fun onCancelButtonClick() {
                        // No se Utiliza en este dialog
                    }

                    override fun onContinueButtonClick() {
                        val intent = Intent(requireContext(),MainActivity::class.java)
                        startActivity(intent)
                        activity!!.finish()
                    }
                })
            }
        })
    }
}
