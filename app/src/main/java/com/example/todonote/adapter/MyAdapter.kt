package com.example.todonote.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.todonote.AppConstant
import com.example.todonote.database.Notes
import com.example.todonote.database.NotesDao
import com.example.todonote.database.NotesDatabase
import com.example.todonote.databinding.DeleteDialogBinding
import com.example.todonote.databinding.RecyclerLayoutBinding
import com.example.todonote.view.ActivityShowNote
import com.example.todonote.view.MainActivity


class MyAdapter(
    val context: Context,
    var list: MutableList<Notes>,
    private val Listener: OnItemClickListener
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var binding = RecyclerLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.textViewTitleNote.text = list[position].title
        holder.binding.recyclerTimeTextView.text =  list[position].time



        val notesDao: NotesDao = NotesDatabase.buildDatabase(context).getNotesDao()



        holder.binding.constraintlayout.setOnClickListener{



            val adapterTime=list[position].time

            val intent = Intent(context, ActivityShowNote::class.java)
            intent.putExtra(AppConstant.time, adapterTime)
            context.startActivity(intent)


        }

        holder.binding.recyclerDeleteImageView.setOnClickListener {


            val time=list[position].time

            val view: DeleteDialogBinding = DeleteDialogBinding.inflate(LayoutInflater.from(context))
            val alertDialog: AlertDialog = AlertDialog.Builder(context).setView(view.root).create()
            alertDialog.setCancelable(false)
            alertDialog.show()
            view.okButton.setOnClickListener {
                notesDao.deleteWithTime(time)
                context.startActivity(Intent(context, MainActivity::class.java))
            }


            view.cancelButton.setOnClickListener {
                alertDialog.hide()

            }



        }


    }

    override fun getItemCount(): Int {
        return list.size

    }


    inner class MyViewHolder(val binding: RecyclerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) Listener.OnItemClick(position)

        }


    }

    interface OnItemClickListener {

        fun OnItemClick(position: Int)

    }




}