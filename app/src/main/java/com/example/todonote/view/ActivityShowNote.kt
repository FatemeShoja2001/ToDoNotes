package com.example.todonote.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.todonote.AppConstant
import com.example.todonote.R
import com.example.todonote.database.Notes
import com.example.todonote.database.NotesDao
import com.example.todonote.database.NotesDatabase
import com.example.todonote.databinding.ActivityShowNoteBinding
import com.example.todonote.databinding.DeleteDialogBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ActivityShowNote : AppCompatActivity() {
    lateinit var binding: ActivityShowNoteBinding
    private lateinit var notesDao: NotesDao
    private lateinit var notes: Notes
    private lateinit var adapterTime: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        notesDao = NotesDatabase.buildDatabase(this).getNotesDao()


        val intentFromAdapter = intent
        adapterTime = intentFromAdapter.getStringExtra(AppConstant.time).toString()
        val list = notesDao.getNotesWithTime(adapterTime)


        list.forEach {
            binding.editTextTitle.setText(it.title)
            binding.editTextDes.setText(it.description)
            binding.timeTextView.setText(it.time)
            binding.timeTextView.visibility = View.VISIBLE
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

                notesDao.deleteWithTime(adapterTime)
                val formatter = DateTimeFormatter.ofPattern(" yyy-MM-dd   HH:mm:ss ")
                val current = LocalDateTime.now().format(formatter)

                notes = Notes(title = title, description = des, time = current)
                notesDao.insert(notes)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()


            }
        }






        binding.backImageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

//        binding.DeleteImageView.setOnClickListener {
//
//            deleteDialog()
//
//
//        }
//
//
//    }
//
//
//    fun deleteDialog() {
//
//        val view: DeleteDialogBinding = DeleteDialogBinding.inflate(LayoutInflater.from(this))
//        val alertDialog: AlertDialog = AlertDialog.Builder(this).setView(view.root).create()
//        alertDialog.setCancelable(false)
//        alertDialog.show()
//        view.okButton.setOnClickListener {
//            notesDao.deleteWithTime(adapterTime)
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//
//        }
//
//
//        view.cancelButton.setOnClickListener {
//            alertDialog.hide()
//
//        }
//    }

    }