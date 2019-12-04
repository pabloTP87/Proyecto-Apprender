package com.example.apprender.view

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.apprender.R
import com.example.apprender.logica.Session
import com.example.apprender.view.supportClasses.IntroScreenItems
import com.example.apprender.view.adapters.IntroViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_walkthrough.*

class WalkthroughActivity : AppCompatActivity() {

    private lateinit var screenPager: ViewPager
    private lateinit var introViewPagerAdapter: IntroViewPagerAdapter
    private lateinit var tabIndicator: TabLayout
    private var position: Int = 0
    lateinit var session: Session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        session = Session(this)

        val introViewPager = findViewById<ViewPager>(R.id.intro_view_pager)
        val indicatorDots = findViewById<TabLayout>(R.id.indicators_dots)

        // Acciones del viewPager e instrucciones de audio
        val bienvenida1 = MediaPlayer.create(this,R.raw.bienvenida_1)
        val bienvenida2 = MediaPlayer.create(this,R.raw.bienvenida_2)
        val bienvenida3 = MediaPlayer.create(this,R.raw.bienvenida_3)

        /* La activity Intro debe verificar antes de abrir si ya fue
        desplegada anteriormente mediante la variable guardada en SharedPreferences -> True o False
         */
        if (session.verificarIntroCheckData()) {

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            bienvenida1.pause()

            finish()
        }

        // Creamos la lista de elementos del ViewPager
        val item1 = IntroScreenItems(
            "Bienvenido",
            getString(R.string.description1),
            R.drawable.intro_img_1
        )
        val item2 = IntroScreenItems(
            "Interactua",
            getString(R.string.description2),
            R.drawable.intro_img_2
        )
        val item3 = IntroScreenItems(
            "Supera los niveles",
            getString(R.string.description3),
            R.drawable.intro_img_3
        )

        val listItems = ArrayList<IntroScreenItems>()

        listItems.add(item1)
        listItems.add(item2)
        listItems.add(item3)

        // Configurar ViewPager
        screenPager = introViewPager
        introViewPagerAdapter = IntroViewPagerAdapter(this, listItems)
        screenPager.adapter = introViewPagerAdapter

        // Configurar TabLayout
        tabIndicator = indicatorDots
        tabIndicator.setupWithViewPager(screenPager)

        if (screenPager.currentItem == 0){
            bienvenida1.start()
        }

        screenPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageSelected(position: Int) {
                when(position){

                    0 ->{
                        bienvenida1.seekTo(0)
                        bienvenida1.start()

                        if (bienvenida1.isPlaying){
                            bienvenida2.pause()
                        }
                    }

                    1 ->{
                        bienvenida2.seekTo(0)
                        bienvenida2.start()

                        if (bienvenida2.isPlaying){
                            bienvenida1.pause()
                        }

                        if (bienvenida3.isPlaying){
                            bienvenida3.pause()
                        }
                    }

                    2 ->{
                        bienvenida3.seekTo(0)
                        bienvenida3.start()

                        if (bienvenida3.isPlaying){
                            bienvenida1.pause()
                            bienvenida2.pause()
                        }
                    }
                }
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageScrollStateChanged(position: Int) {

            }

        })


        tabIndicator.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab!!.position > 0 ){

                    btn_move_left.visibility = View.VISIBLE
                }else{
                    btn_move_left.visibility = View.INVISIBLE
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

        })

        btn_move_right.setOnClickListener {
            // screenPager.currentItem: devuelve la posición actual del viewPager 0,1 o 2
            position = screenPager.currentItem

            if (position < listItems.size){

                position++
                screenPager.currentItem = position
                // Mostramos el boton para desplazar el viewPager hacia la izquierda
                btn_move_left.visibility = View.VISIBLE
            }
        }

        btn_move_left.setOnClickListener {

            position = screenPager.currentItem

            if (position <= listItems.size){

                position--
                screenPager.currentItem = position
            }

            if (position == 0){

                btn_move_left.visibility = View.INVISIBLE
            }
        }

        btn_start.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            if(bienvenida1.isPlaying || bienvenida2.isPlaying || bienvenida3.isPlaying){
                bienvenida1.pause()
                bienvenida2.pause()
                bienvenida3.pause()
            }

            startActivity(intent)

            session.savePrefIntroData()

            finish()
        }
    }
}
