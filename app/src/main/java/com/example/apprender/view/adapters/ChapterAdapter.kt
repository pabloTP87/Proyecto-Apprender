package com.example.apprender.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.apprender.R
import com.example.apprender.logica.CustomDialog
import com.example.apprender.view.ChapterOneActivity
import com.example.apprender.view.ChapterTwoActivity
import com.example.apprender.view.IntroEncuestaActivity
import com.example.apprender.view.supportClasses.ItemsChapterCard

class ChapterAdapter(val context: Context, private val chapterDataList: ArrayList<ItemsChapterCard>, private val clickListener: (ItemsChapterCard) -> Unit) : RecyclerView.Adapter<ChapterAdapter.viewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): viewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.chapter_card, viewGroup, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {

        return chapterDataList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(chapterDataList[position], clickListener)
    }

    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        internal var chapterTitle: TextView
        internal var chapterDescription: TextView
        internal var chapterImg: ImageView
        internal var chapterCard: CardView

        init {
            chapterTitle = itemView.findViewById(R.id.titleCard) as TextView
            chapterDescription = itemView.findViewById(R.id.descriptionCard) as TextView
            chapterImg = itemView.findViewById(R.id.imageCard) as ImageView
            chapterCard = itemView.findViewById(R.id.cardView) as CardView
        }

        fun bind(item: ItemsChapterCard, clickListener: (ItemsChapterCard) -> Unit){
            chapterTitle.text = item.title
            chapterDescription.text = item.description
            chapterImg.setImageResource(item.img)
            itemView.setOnClickListener { clickListener(item) }
        }
    }
}

