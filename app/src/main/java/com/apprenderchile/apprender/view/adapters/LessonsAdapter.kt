package com.apprenderchile.apprender.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.apprenderchile.apprender.R
import com.apprenderchile.apprender.view.*
import com.apprenderchile.apprender.view.supportClasses.ItemsLessonsList

class LessonsAdapter (private val context: Context, private val lessonsList: ArrayList<ItemsLessonsList>, private val clickListener: (ItemsLessonsList) -> Unit) : RecyclerView.Adapter<LessonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lessons_list,viewGroup,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(lessonsList[position], clickListener)
        holder.audioButton.setOnClickListener {

            when (position){
                0 -> {
                    if (context is ChapterOneActivity){
                        Toast.makeText(context,"audio lección vocales 1",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"audio lección sílabas 1",Toast.LENGTH_SHORT).show()
                    }
                }

                1 -> {
                    if (context is ChapterOneActivity){
                        Toast.makeText(context,"audio lección vocales 2",Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(context,"audio lección sílabas 2",Toast.LENGTH_SHORT).show()
                    }
                }

                2 -> {
                    if (context is ChapterOneActivity){
                        Toast.makeText(context,"audio lección vocales 3",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"audio lección sílabas 3",Toast.LENGTH_SHORT).show()
                    }
                }

                3 -> {
                    Toast.makeText(context,"audio lección sílabas 4",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

   inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var title: TextView
        internal var description: TextView
        internal var audioButton: Button
        internal var lessonCard: CardView

        init {
            title = itemView.findViewById(R.id.title) as TextView
            description = itemView.findViewById(R.id.description) as TextView
            audioButton = itemView.findViewById(R.id.audio_btn) as Button
            lessonCard = itemView.findViewById(R.id.lesson_card) as CardView
        }

       fun bind(item: ItemsLessonsList, clickListener: (ItemsLessonsList) -> Unit){
           title.text = item.tittle
           description.text = item.description
           itemView.setOnClickListener { clickListener(item) }
       }
    }
}