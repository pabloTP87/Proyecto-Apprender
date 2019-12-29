package com.apprenderchile.apprender.logica

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.apprenderchile.apprender.view.LoginActivity

class Session(var context: Context) {

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor
    private var PRIVATE_MODE: Int = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object{
        val PREF_NAME: String = "myPref"
        val IS_LOGIN: String = "isLoggedIn"
        val IS_INTRO: String = "introIsOpen"
        val KEY_NAME: String = "nombre"
        val KEY_RUT: String = "rut"
    }

    fun createLoginSession (nombre: String, rut: String){
        editor.putBoolean(IS_LOGIN,true)
        editor.putString(KEY_NAME,nombre)
        editor.putString(KEY_RUT,rut)
        editor.commit()
    }

    fun checkLogin(){

        if (!this.isLoggedIn()){

            val intent = Intent(context,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }
    }

    fun getUserData(): HashMap<String,String> {
        val user = HashMap<String,String>()
        user.set(KEY_NAME, pref.getString(KEY_NAME, null)!!)
        user.set(KEY_RUT, pref.getString(KEY_RUT, null)!!)

        return user
    }

    fun logoutUser(){

        editor.clear()
        editor.commit()

        val intent = Intent(context,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        savePrefIntroData()
    }

    fun isLoggedIn(): Boolean{

        return pref.getBoolean(IS_LOGIN,false)
    }

    fun verificarIntroCheckData() : Boolean {

        return pref.getBoolean(IS_INTRO,false)
    }

    fun savePrefIntroData() {

        editor.putBoolean(IS_INTRO, true)
        editor.apply()
    }

}