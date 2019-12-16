package com.example.apprender.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apprender.R
import com.example.apprender.view.supportClasses.LeccionOneProfile
import kotlinx.android.synthetic.main.leccion_profile_list.view.*

class LeccionOneProfileAdapter(private val context: Context): RecyclerView.Adapter<LeccionOneProfileAdapter.LeccionOneViewHolder>() {

    private var dataList = mutableListOf<LeccionOneProfile>()

    fun setListData(data: MutableList<LeccionOneProfile>){
        dataList = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeccionOneViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.leccion_profile_list,parent,false)
        return LeccionOneViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }

    override fun onBindViewHolder(holder: LeccionOneViewHolder, position: Int) {
        val leccionProfile: LeccionOneProfile = dataList[position]
        holder.bindView(leccionProfile)
    }

    inner class LeccionOneViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(leccionProfile: LeccionOneProfile){
            itemView.title.text = leccionProfile.titulo
            itemView.puntaje.text = leccionProfile.puntaje
            itemView.img.setImageResource(leccionProfile.img)
        }
    }

}