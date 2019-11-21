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

class ChapterTwoActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mRecyclerAdapter: RecyclerView.Adapter<*>? = null
    private var lessonsList: ArrayList<ItemsLessonsList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_two)

        lessonsList.add(ItemsLessonsList("Separando las sílabas","Lee las sílabas"))
        lessonsList.add(ItemsLessonsList("Marcando las sílabas","Marca las sílabas estudiadas"))
        lessonsList.add(ItemsLessonsList("Lee palabras","Reconoce y lee las palabras"))
        lessonsList.add(ItemsLessonsList("Lee oraciones","Reconoce y lee oraciones"))

        mRecyclerView = findViewById(R.id.lessonsList)
        val mLayoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerAdapter = LessonsAdapter(this,lessonsList)
        mRecyclerView!!.adapter = mRecyclerAdapter
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
