package com.example.apprender.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprender.R
import com.example.apprender.logica.Session
import com.example.apprender.view.adapters.LessonsAdapter
import com.example.apprender.view.supportClasses.ItemsLessonsList
import com.example.apprender.viewmodel.EstadoLeccionViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.lessons_list.*

class ChapterOneActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RecyclerView.Adapter<*>? = null
    private val lessonList: ArrayList<ItemsLessonsList> = ArrayList()

    lateinit var viewModel: EstadoLeccionViewModel
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_one)

        session = Session(this)
        val rut = session.getUserData()[Session.KEY_RUT]
        viewModel = ViewModelProviders.of(this)[EstadoLeccionViewModel::class.java]

        lessonList.add(ItemsLessonsList("Reconociendo las vocales","Selecciona la vocal"))
        lessonList.add(ItemsLessonsList("Asociando imágenes","Selecciona la imagen correcta"))
        lessonList.add(ItemsLessonsList("Completa las vocales","Completa la palabra con vocales"))

        recyclerView = findViewById(R.id.lessonsList)
        val mLayoutManager = GridLayoutManager(this,1, GridLayoutManager.VERTICAL, false)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerAdapter = LessonsAdapter(this,lessonList){

            when (lessonList.indexOf(it)){

                0 -> {
                    viewModel.getLeccionUsuario(rut!!,"capitulo_1", lessonList.indexOf(it))
                }

                1 ->{
                    viewModel.getLeccionUsuario(rut!!,"capitulo_1", lessonList.indexOf(it))
                }

                2 ->{
                    viewModel.getLeccionUsuario(rut!!,"capitulo_1", lessonList.indexOf(it))
                }
            }
        }

        observeFlujoLeccion()

        recyclerView!!.adapter = recyclerAdapter
        // mostramos y habilitamos el boton atrás en el Navbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    private fun observeFlujoLeccion(){
        viewModel.fetchEstadoLeccion().observe(this, Observer {
            val leccion = it[0] // N° de leccion consultado en Firestore
            val estado = it[1] // Estado de la lección = "enabled", "disabled", "success"

            when (leccion){
                "leccion_1" -> {
                    when (estado){
                        "enabled" -> {
                            val intent= Intent(this,LeccionVocalesOneActivity::class.java)
                            startActivity(intent)
                        }
                        "disabled" -> {
                            Toast.makeText(this,"Lección no disponible",Toast.LENGTH_SHORT).show()
                        }
                        "success" -> {
                            Toast.makeText(this,"Ya has superado esta lección",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "leccion_2" -> {
                    when (estado){
                        "enabled" -> {
                            val intent= Intent(this,LeccionVocalesTwoActivity::class.java)
                            startActivity(intent)
                        }
                        "disabled" -> {
                            Toast.makeText(this,"Lección no disponible",Toast.LENGTH_SHORT).show()
                        }
                        "success" -> {
                            Toast.makeText(this,"Ya has superado esta lección",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "leccion_3" -> {
                    when (estado){
                        "enabled" -> {
                            val intent= Intent(this,LeccionVocalesThreeActivity::class.java)
                            startActivity(intent)
                        }
                        "disabled" -> {
                            Toast.makeText(this,"Lección no disponible",Toast.LENGTH_SHORT).show()
                        }
                        "success" -> {
                            Toast.makeText(this,"Ya has superado esta lección",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    // Evento que finaliza esta actividad al presionar el boton atrás en el navbar
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId

        if (id == android.R.id.home){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // evento que finaliza esta actividad al presionar el boton atrás del móvil
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
