package com.example.apprender.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.apprender.R
import com.example.apprender.logica.Session
import com.example.apprender.view.ChapterTwoActivity
import com.example.apprender.view.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.encuesta_confirm_dialog.view.*
import kotlinx.android.synthetic.main.encuesta_confirm_dialog.view.btn_continuar
import kotlinx.android.synthetic.main.leccion_confirm_dialog.view.*

class EncuestaConfirmFragment : Fragment() {

    val db = FirebaseFirestore.getInstance()
    lateinit var session: Session

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        session = Session(requireContext())

        val view = inflater.inflate(R.layout.fragment_encuesta_confirm, container, false)

        val btnAudioConfirm = view.findViewById<Button>(R.id.btn_audio_confirm_encuesta)
        val btnGuardar = view.findViewById<Button>(R.id.btn_Finalizar)

        val respuestaOne = arguments?.getString("respuesta_one")
        val respuestaTwo = arguments?.getString("respuesta_two")
        val respuestaThree = arguments?.getString("respuesta_three")

        btnGuardar.setOnClickListener {

            saveEncuesta(respuestaOne!!, respuestaTwo!!, respuestaThree!!)
        }
        // Inflate the layout for this fragment
        return view
    }

    fun saveEncuesta(respuesta1: String, respuesta2: String, respuesta3: String){

        val userData = session.getUserData()
        val rut = userData.get(Session.KEY_RUT)

        val encuesta = hashMapOf(
            "respuesta_1" to respuesta1,
            "respuesta_2" to respuesta2,
            "respuesta_3" to respuesta3
        )

        db.collection("usuarios").document(rut!!).collection("encuesta")
            .document("encuesta - $rut").set(encuesta).addOnCompleteListener {
                if (it.isSuccessful){

                    Log.d("Documento agregado","$encuesta")

                    val confirmDialog = LayoutInflater.from(this.context).inflate(R.layout.encuesta_confirm_dialog,null)
                    val builder = AlertDialog.Builder(this.activity).setView(confirmDialog)

                    val alertDialog = builder.show()

                    confirmDialog.btn_continuar.setOnClickListener {
                        alertDialog.dismiss()

                        val intent = Intent(this.activity, MainActivity::class.java)
                        startActivity(intent)
                    }

                }else{
                    Log.e("Save error", "No se pudo guardar el usuario")
                }
            }
    }


}
