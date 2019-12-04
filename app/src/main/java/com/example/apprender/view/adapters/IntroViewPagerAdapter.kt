package com.example.apprender.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.apprender.R
import com.example.apprender.view.supportClasses.IntroScreenItems
import kotlinx.android.synthetic.main.layout_intro_screen.view.*

class IntroViewPagerAdapter (var context: Context, var listItems: List<IntroScreenItems>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutIntroScreen: View = layoutInflater.inflate(R.layout.layout_intro_screen, null)

        val imgItem: ImageView = layoutIntroScreen.intro_img
        val txtTitle: TextView = layoutIntroScreen.intro_tittle
        val txtDescription: TextView = layoutIntroScreen.intro_description

        imgItem.setImageResource(listItems[position].img)
        txtTitle.text = listItems[position].Title
        txtDescription.text = listItems[position].description

        container.addView(layoutIntroScreen)

        return layoutIntroScreen

    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return listItems.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}