package com.apprenderchile.apprender.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.logica.Session
import com.apprenderchile.apprender.view.adapters.LessonsAdapter
import com.apprenderchile.apprender.view.supportClasses.ItemsLessonsList
import com.apprenderchile.apprender.viewmodel.EstadoLeccionViewModel

class ChapterTwoActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mRecyclerAdapter: RecyclerView.Adapter<*>? = null
    private var lessonsList: ArrayList<ItemsLessonsList> = ArrayList()

    lateinit var viewModel: EstadoLeccionViewModel
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_two)

        session = Session(this)
        val rut = session.getUserData()[Session.KEY_RUT]
        viewModel = ViewModelProviders.of(this)[EstadoLeccionViewModel::class.java]

        lessonsList.add(ItemsLessonsList("Separando las sílabas","Lee las sílabas"))
        lessonsList.add(ItemsLessonsList("Marcando las sílabas","Marca las sílabas estudiadas"))
        lessonsList.add(ItemsLessonsList("Lee palabras","Reconoce y lee las palabras"))
        lessonsList.add(ItemsLessonsList("Lee oraciones","Reconoce y lee oraciones"))

        mRecyclerView = findViewById(R.id.lessonsList)
        val mLayoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerAdapter = LessonsAdapter(this,lessonsList){

            when (lessonsList.indexOf(it)){

                0 -> {
                    viewModel.getLeccionUsuario(rut!!,"capitulo_2", lessonsList.indexOf(it))
                }

                1 ->{
                    viewModel.getLeccionUsuario(rut!!,"capitulo_2", lessonsList.indexOf(it))
                }

                2 ->{
                    viewModel.getLeccionUsuario(rut!!,"capitulo_2", lessonsList.indexOf(it))
                }

                3 ->{
                    viewModel.getLeccionUsuario(rut!!,"capitulo_2", lessonsList.indexOf(it))
                }
            }
        }

        observeFlujoLeccion()

        mRecyclerView!!.adapter = mRecyclerAdapter

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
                            val intent= Intent(this,LeccionSilabasOneActivity::class.java)
                            startActivity(intent)
                        }
                        "disabled" -> {
                            Toast.makeText(this,"Lección no disponible", Toast.LENGTH_SHORT).show()
                        }
                        "success" -> {
                            Toast.makeText(this,"Ya has superado esta lección", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "leccion_2" -> {
                    when (estado){
                        "enabled" -> {
                            val intent= Intent(this,LeccionSilabasTwoActivity::class.java)
                            startActivity(intent)
                        }
                        "disabled" -> {
                            Toast.makeText(this,"Lección no disponible", Toast.LENGTH_SHORT).show()
                        }
                        "success" -> {
                            Toast.makeText(this,"Ya has superado esta lección", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "leccion_3" -> {
                    when (estado){
                        "enabled" -> {
                            val intent= Intent(this,LeccionLecturaOneActivity::class.java)
                            startActivity(intent)
                        }
                        "disabled" -> {
                            Toast.makeText(this,"Lección no disponible", Toast.LENGTH_SHORT).show()
                        }
                        "success" -> {
                            Toast.makeText(this,"Ya has superado esta lección", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                "leccion_4" -> {
                    when (estado){
                        "enabled" -> {
                            val intent= Intent(this,LeccionLecturaTwoActivity::class.java)
                            startActivity(intent)
                        }
                        "disabled" -> {
                            Toast.makeText(this,"Lección no disponible", Toast.LENGTH_SHORT).show()
                        }
                        "success" -> {
                            Toast.makeText(this,"Ya has superado esta lección", Toast.LENGTH_SHORT).show()
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
