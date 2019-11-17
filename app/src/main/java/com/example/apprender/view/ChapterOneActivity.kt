package com.example.apprender.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.apprender.R
import com.example.apprender.view.adapters.LessonsAdapter
import com.example.apprender.view.supportClasses.ItemsLessonsList

class ChapterOneActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var recyclerAdapter: RecyclerView.Adapter<*>? = null
    private var lessonList: ArrayList<ItemsLessonsList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_one)

        lessonList.add(ItemsLessonsList("Reconociendo las vocales","Selecciona la vocal" ))
        lessonList.add(ItemsLessonsList("Asociando imágenes","Selecciona la imagen correcta" ))
        lessonList.add(ItemsLessonsList("Completa las vocales","Completa la palabra con vocales" ))

        recyclerView = findViewById(R.id.lessonsList)
        val mLayoutManager = GridLayoutManager(this,1, GridLayoutManager.VERTICAL, false)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerAdapter = LessonsAdapter(this,lessonList)
        recyclerView!!.adapter = recyclerAdapter
        // mostramos y habilitamos el boton atrás en el Navbar
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
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
