package com.example.todonote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todonote.databinding.ActivitySplashBinding
import com.example.todonote.view.LoginActivity
import com.example.todonote.view.MainActivity

class Splash : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivitySplashBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


       val userName = App.preferences.getString(App.user_name,"")
        val password= App.preferences.getString(App.password,"")


        if (userName.equals("")||password.equals("")){
            startActivity(Intent(this, LoginActivity::class.java))

        }else    {  startActivity(Intent(this, MainActivity::class.java))
                    finish()}
    }
}