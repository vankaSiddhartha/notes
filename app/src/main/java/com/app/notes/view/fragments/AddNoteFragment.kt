package com.app.notes.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.notes.R
import com.app.notes.databinding.FragmentAddnoteBinding
import com.app.notes.model.NotesModel
import com.app.notes.view.adapter.NotesAdapter
import com.app.notes.viewModel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class AddNoteFragment : Fragment() {
 private lateinit var binding:FragmentAddnoteBinding
 private lateinit var viewModel:NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddnoteBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
         binding.dateTimeText.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        // Inflate the layout for this fragment
        val title = arguments?.getString("title")
        val content = arguments?.getString("content")
        val id = arguments?.getInt("id")
         if (title!=null && content !=null){
             binding.titleEditText.setText(title.toString())
             binding.contentEditText.setText(content)
             binding.wordCountText.text = content.length.toString()+" words"
             binding.contentEditText.setSelection(content.length)

         }
        binding.backButton.setOnClickListener {
            navigationBackWithDataSavedInDB(id)
        }
        binding.dustbin.setOnClickListener {
            if (id != null) {
              showDeleteConfirmationDialog(id)
            }else{
                navigateBack()
            }
        }
        binding.contentEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Update word count after the text changes
                binding.wordCountText.text =  s.toString().length.toString()+" words"
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed before text changes
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed while the text is changing
            }
        })
        binding.save.setOnClickListener {

            val title = binding.titleEditText.text.toString()
            val note = binding.contentEditText.text.toString()
            val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
            if (id != null && title.isNotEmpty() && note.isNotEmpty()){
                val newNote = NotesModel(id = id.toInt() ,note = note, title = title, time = time)

                viewModel.updateNote(newNote)
                Toast.makeText(requireContext(), "updated", Toast.LENGTH_SHORT).show()
                navigateBack()

            }else if (title.isNotEmpty() && note.isNotEmpty()) {
                val newNote = NotesModel(note = note, title = title, time = time)

                Toast.makeText(requireContext(), "saved", Toast.LENGTH_SHORT).show()
                viewModel.insertNote(newNote)

            }
        }

        return binding.root
    }
    private fun showDeleteConfirmationDialog(id:Int) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete Confirmation")
        alertDialogBuilder.setMessage("Are you sure you want to delete?")
        alertDialogBuilder.setPositiveButton("Delete") { dialog, which ->
            deleteItem(id)
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            // Dismiss dialog or handle cancel action
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    // Example function for handling delete action
    private fun deleteItem(id:Int) {
        // Perform delete operation here
        val title = binding.titleEditText.text.toString()
        val note = binding.contentEditText.text.toString()
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        if (title.isNotEmpty() && note.isNotEmpty()){
            val newNote = NotesModel(id = id.toInt() ,note = note, title = title, time = time)

            viewModel.deleteNote(newNote.id)
            Toast.makeText(requireContext(), "deleted", Toast.LENGTH_SHORT).show()
            navigateBack()
        }else{
            Toast.makeText(requireContext(), "The text is not saved", Toast.LENGTH_SHORT).show()
        }
    }
    private fun navigateBack() {
        // Get the FragmentManager
          val fragment = HomeFragment()
            // Pop the back stack to go back to the previous fragment
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }
    private fun navigationBackWithDataSavedInDB(id: Int?){
        val title = binding.titleEditText.text.toString()
        val note = binding.contentEditText.text.toString()
        val time = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        if (id != null && title.isNotEmpty() && note.isNotEmpty()){
            val newNote = NotesModel(id = id.toInt() ,note = note, title = title, time = time)

            viewModel.updateNote(newNote)
           // Toast.makeText(requireContext(), "updated", Toast.LENGTH_SHORT).show()
            navigateBack()

        }else if (title.isNotEmpty() && note.isNotEmpty()) {
            val newNote = NotesModel(note = note, title = title, time = time)

           // Toast.makeText(requireContext(), "saved", Toast.LENGTH_SHORT).show()
            viewModel.insertNote(newNote)

        }
        navigateBack()
    }


}