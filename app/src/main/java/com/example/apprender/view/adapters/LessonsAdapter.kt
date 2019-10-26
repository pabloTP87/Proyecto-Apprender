package com.example.apprender.view.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.apprender.R
import com.example.apprender.view.LeccionVocalesOneActivity
import com.example.apprender.view.LeccionVocalesThreeActivity
import com.example.apprender.view.LeccionVocalesTwoActivity
import com.example.apprender.view.supportClasses.ItemsLessonsList

class LessonsAdapter (private val context: Context, private val lessonsList: ArrayList<ItemsLessonsList>) : RecyclerView.Adapter<LessonsAdapter.viewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LessonsAdapter.viewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lessons_list,viewGroup,false)

        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return lessonsList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.text = lessonsList[position].tittle
        holder.description.text = lessonsList[position].description

        when (position){

            0 -> {
                holder.lessonCard.setOnClickListener {

                    val intent = Intent(context,LeccionVocalesOneActivity::class.java)
                    context.startActivity(intent)
                }

                holder.audioButton.setOnClickListener {
                    val toast = Toast.makeText(context,"audio lección 1",Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            1 ->{
                holder.lessonCard.setOnClickListener {

                    val intent = Intent(context, LeccionVocalesTwoActivity::class.java)
                    context.startActivity(intent)
                }

                holder.audioButton.setOnClickListener {
                    val toast = Toast.makeText(context,"audio lección 2",Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            2 ->{
                holder.lessonCard.setOnClickListener {

                    val intent = Intent(context, LeccionVocalesThreeActivity::class.java)
                    context.startActivity(intent)
                }

                holder.audioButton.setOnClickListener {
                    val toast = Toast.makeText(context,"audio lección 3",Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

        }
    }

    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var title: TextView
        internal var description: TextView
        internal var audioButton: Button
        internal var img: ImageView
        internal var lessonCard: CardView

        init {
            title = itemView.findViewById(R.id.title) as TextView
            description = itemView.findViewById(R.id.description) as TextView
            audioButton = itemView.findViewById(R.id.btn_audio_lesson) as Button
            img = itemView.findViewById(R.id.img_estado) as ImageView
            lessonCard = itemView.findViewById(R.id.lesson_card) as CardView
        }
    }

}