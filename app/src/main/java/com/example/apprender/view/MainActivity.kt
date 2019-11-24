package com.example.apprender.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.apprender.R
import com.example.apprender.logica.Validator
import com.example.apprender.view.fragments.HelpFragment
import com.example.apprender.view.fragments.HomeFragment
import com.example.apprender.view.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager
    val validator : Validator = Validator()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.nav_home -> {
                val homeFragment = HomeFragment()
                createFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_profile -> {
                val profileFragment = ProfileFragment()
                createFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_help -> {
                val helpFragment = HelpFragment()
                createFragment(helpFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        validator.checkPermision(this , this)

        val homeFragment = HomeFragment()
        createFragment(homeFragment)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun createFragment(fragment: Fragment){
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_fragment_container,fragment)
        transaction.commit()

    }
}
