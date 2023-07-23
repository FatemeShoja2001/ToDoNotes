package com.example.todonote.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.todonote.App
import com.example.todonote.AppConstant
import com.example.todonote.R
import com.example.todonote.databinding.ActivityLogInBinding

class LoginActivity : AppCompatActivity() {


    lateinit var binding:ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonLogIn.setOnClickListener {

            val name=binding.editTextUsreName.text.toString()
            val pass=binding.editTextPassword.text.toString()


            if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pass) || name.startsWith(" ") || pass.startsWith(" ")) {


                Toast.makeText(this,getString(R.string.toast_theValuesCanNotBeEmpty), Toast.LENGTH_SHORT).show()


            }

            else {

                App.editor.putString(App.user_name,name)
                App.editor.putString(App.password,pass)
                App.editor.apply()

                startActivity(Intent(this, MainActivity::class.java))}


        }




    }
}