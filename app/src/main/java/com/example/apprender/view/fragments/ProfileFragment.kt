package com.example.apprender.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apprender.R
import com.example.apprender.logica.Session
import com.example.apprender.view.adapters.LeccionOneProfileAdapter
import com.example.apprender.viewmodel.EstadoLeccionViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var session: Session
    private val viewModel by lazy { ViewModelProviders.of(requireActivity())[EstadoLeccionViewModel::class.java] }

    private lateinit var adapter: LeccionOneProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_profile, container, false)

        session = Session(requireContext())
        val nombre = session.getUserData()[Session.KEY_NAME]
        val rut = session.getUserData()[Session.KEY_RUT]

        val saludo = "Hola $nombre"
        val saludoUser = view.saludo_usuario
        saludoUser.text = saludo

        adapter = LeccionOneProfileAdapter(requireContext())
        view.recycler_leccion1.layoutManager = LinearLayoutManager(requireContext())
        view.recycler_leccion1.adapter = adapter

        view!!.profile_bar.visibility = View.VISIBLE
        observePuntajeLeccion(rut!!)

        // Inflate the layout for this fragment
        return view
    }

    private fun observePuntajeLeccion(rut: String){

        viewModel.fetchPuntajeLeccion(rut).observe(requireActivity(), Observer {
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
            if (adapter.itemCount == 0){
                view!!.txt_mensaje.visibility = View.VISIBLE
            }
            view!!.profile_bar.visibility = View.GONE
        })
    }
}
