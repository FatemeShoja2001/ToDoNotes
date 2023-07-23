package com.example.todonote

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.todonote.App.Companion.preferences

class App:Application() {
    companion object{
    lateinit var preferences :SharedPreferences
    lateinit var editor :SharedPreferences.Editor
        const val user_name="user_name" //key
        const val password="password"


    }

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        preferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        editor= preferences.edit()
        editor.apply()


    }

}