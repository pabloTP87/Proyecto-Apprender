package com.example.apprender.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apprender.R
import com.example.apprender.view.supportClasses.ItemsChapterCard
import com.example.apprender.view.adapters.ChapterAdapter

class HomeFragment : Fragment() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var chapterList: ArrayList<ItemsChapterCard> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        chapterList.add(
            ItemsChapterCard(
                "Capitulo 1",
                "Lectura dinámica",
                R.drawable.img_chapter_one
            )
        )
        chapterList.add(
            ItemsChapterCard(
                "Capitulo 2",
                "Asociación de imágenes",
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
        mAdapter = ChapterAdapter(view.context,chapterList)
        mRecyclerView!!.adapter = mAdapter
        // Inflate the layout for this fragment
        return view
    }


}
