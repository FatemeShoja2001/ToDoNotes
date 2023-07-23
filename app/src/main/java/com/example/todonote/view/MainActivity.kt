package com.example.todonote.view
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todonote.adapter.MyAdapter
import com.example.todonote.database.NotesDao
import com.example.todonote.database.NotesDatabase
import com.example.todonote.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),View.OnClickListener, MyAdapter.OnItemClickListener {


    lateinit var binding: ActivityMainBinding

    private lateinit var notesDao: NotesDao   //codes of Database in MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.title = App.preferences.getString(App.user_name, "")
        supportActionBar?.hide()


        notesDao = NotesDatabase.buildDatabase(this).getNotesDao() //codes of Database in MainActivity

        binding.fabButton.setOnClickListener(this)



        val adapter = MyAdapter(this,  getNotesFromDb() , this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter


        if (adapter.itemCount>0){
        binding.addNoteImageView.visibility=View.INVISIBLE
        binding.addNoteTextView.visibility=View.INVISIBLE}




    }

    override fun onClick(v: View?) {

        if (v == binding.fabButton) {
            startActivity(Intent(this, SaveNoteActivity::class.java))
            finish()
        }


    }

    override fun OnItemClick(position: Int) {



    }


    private fun getNotesFromDb()=notesDao.getAll()

}


