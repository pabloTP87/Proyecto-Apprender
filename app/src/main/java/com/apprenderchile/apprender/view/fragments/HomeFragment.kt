package com.apprenderchile.apprender.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.logica.Session
import com.apprenderchile.apprender.view.ChapterOneActivity
import com.apprenderchile.apprender.view.ChapterTwoActivity
import com.apprenderchile.apprender.view.EncuestaActivity
import com.apprenderchile.apprender.view.supportClasses.ItemsChapterCard
import com.apprenderchile.apprender.view.adapters.ChapterAdapter
import com.apprenderchile.apprender.viewmodel.EstadoLeccionViewModel

class HomeFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var chapterList: ArrayList<ItemsChapterCard> = ArrayList()

    lateinit var session: Session
    lateinit var viewModel: EstadoLeccionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        session = Session(requireContext())
        val rut = session.getUserData()[Session.KEY_RUT]
        viewModel = ViewModelProviders.of(requireActivity())[EstadoLeccionViewModel::class.java]

        chapterList.add(
            ItemsChapterCard(
                "Capitulo 1",
                "Conoce las vocales",
                R.drawable.img_chapter_one
            )
        )
        chapterList.add(
            ItemsChapterCard(
                "Capitulo 2",
                "Sílabas y lectura",
                R.drawable.img_chapter_two
            )
        )
        chapterList.add(
            ItemsChapterCard(
                "Capitulo 3",
                "Encuesta de satisfacción",
                R.drawable.img_chapter_three
            )
        )

        mRecyclerView = view.findViewById(R.id.recycler_chapter) as RecyclerView
        val mLayoutManager = GridLayoutManager(this.activity,1,GridLayoutManager.VERTICAL,false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mAdapter = ChapterAdapter(view.context,chapterList){

            when (chapterList.indexOf(it)){

                0 -> { viewModel.getEstadoForChapter(rut!!,"capitulo_1","leccion_1",chapterList.indexOf(it)) }
                1 -> { viewModel.getEstadoForChapter(rut!!,"capitulo_2","leccion_1",chapterList.indexOf(it)) }
                2 -> { viewModel.getEstadoForChapter(rut!!,"capitulo_2","leccion_4",chapterList.indexOf(it)) }
            }
        }
        mRecyclerView!!.adapter = mAdapter

        observeEstadoForChapter()
        // Inflate the layout for this fragment
        return view
    }

    private fun observeEstadoForChapter(){
        viewModel.fetchEstadoLeccion().observe(this, Observer {
            val posicion = it[0]
            val estado = it[1]

            when (posicion){

                "0" -> {
                    if (estado == "enabled" || estado == "success"){
                        val intent = Intent(requireContext(),ChapterOneActivity::class.java)
                        startActivity(intent)
                    }
                }
                "1" -> {
                    if (estado == "enabled" || estado == "success"){
                        val intent = Intent(requireContext(),ChapterTwoActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(requireContext(),"Capitulo no disponible",Toast.LENGTH_SHORT).show()
                    }
                }
                "2" -> {
                    if (estado == "success"){
                        val intent = Intent(requireContext(),EncuestaActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(requireContext(),"Encuesta no disponible",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
