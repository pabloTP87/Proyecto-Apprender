package com.example.apprender.view.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.apprender.R
import com.example.apprender.view.ChapterOneActivity
import com.example.apprender.view.StartActivity
import com.example.apprender.view.supportClasses.ItemsChapterCard

class ChapterAdapter(val context: Context, val chapterDataList: ArrayList<ItemsChapterCard>) : RecyclerView.Adapter<ChapterAdapter.viewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): viewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.chapter_card, viewGroup, false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {

        return chapterDataList.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.chapterTitle.text = chapterDataList[position].title
        holder.chapterDescription.text = chapterDataList[position].description
        holder.chapterImg.setImageResource(chapterDataList[position].img)

        holder.chapterCard.setOnClickListener {
            when(position){
                0 -> {
                    val intent = Intent(context,ChapterOneActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
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

    }
}
