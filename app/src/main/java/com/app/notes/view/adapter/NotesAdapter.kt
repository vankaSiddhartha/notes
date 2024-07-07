package com.app.notes.view.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.notes.R
import com.app.notes.databinding.NoteItemBinding
import com.app.notes.model.NotesModel
import com.app.notes.view.fragments.AddNoteFragment
//notes adapter to show notes
class NotesAdapter(private var list: List<NotesModel>,private val context: Context,private val activity: FragmentActivity): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    fun update(newList: List<NotesModel>){
        notifyDataSetChanged()
        list = newList
    }
    class ViewHolder( val binding:NoteItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return  list.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fragment = AddNoteFragment()

        holder.binding.noteTitleTextView.text = list[position].title
        holder.binding.noteContentTextView.text = list[position].note.toString()
        holder.binding.noteTimeTextView.text = list[position].time
        val bundle = Bundle()
        bundle.putInt("id", list[position].id)
        bundle.putString("title", list[position].title)
        bundle.putString("content",list[position].note)
        holder.binding.root.setOnClickListener {
           fragment.arguments = bundle
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }


}