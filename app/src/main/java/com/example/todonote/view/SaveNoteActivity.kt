package com.example.todonote.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.todonote.R
import com.example.todonote.database.Notes
import com.example.todonote.database.NotesDao
import com.example.todonote.database.NotesDatabase
import com.example.todonote.databinding.ActivitySaveNoteBinding
import com.example.todonote.databinding.DeleteDialogBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SaveNoteActivity : AppCompatActivity() {


    private lateinit var notesDao: NotesDao
    lateinit var binding: ActivitySaveNoteBinding
    private lateinit var notes: Notes

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySaveNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        notesDao = NotesDatabase.buildDatabase(this).getNotesDao()


        binding.backImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }






        binding.saveImageView.setOnClickListener {

            val title = binding.editTextTitle.text.toString()
            val des = binding.editTextDes.text.toString()



            if (TextUtils.isEmpty(title) || title.startsWith(" "))

                Toast.makeText(
                    this,
                    getString(R.string.toast_theValuesCanNotBeEmpty),
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val formatter = DateTimeFormatter.ofPattern(" yyy-MM-dd   HH:mm:ss ")
                val current = LocalDateTime.now().format(formatter)

                notes = Notes(title = title, description = des, time = current)
                notesDao.insert(notes)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()


            }
        }



    }





}


