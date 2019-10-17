package com.example.apprender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
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
    }
}
