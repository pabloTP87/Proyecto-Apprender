package com.example.apprender.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apprender.R
import com.example.apprender.logica.Session
import com.example.apprender.logica.Validator
import com.example.apprender.view.fragments.HelpFragment
import com.example.apprender.view.fragments.HomeFragment
import com.example.apprender.view.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val manager = supportFragmentManager
    val validator : Validator = Validator()
    lateinit var session: Session

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

        session = Session(this)

        val homeFragment = HomeFragment()
        createFragment(homeFragment)

        session.checkLogin()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun createFragment(fragment: Fragment){
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_fragment_container,fragment)
        transaction.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.close_session_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item?.itemId

        if (id == R.id.close_session){
            session.logoutUser()
            finish()
        }

        if (id == R.id.check_session){
            val userData = session.getUserData()

            val nombre = userData[Session.KEY_NAME]
            val rut = userData[Session.KEY_RUT]

            Toast.makeText(this,"nombre: $nombre rut: $rut",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
        finish()
    }
}
