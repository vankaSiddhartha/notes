package com.app.notes.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.notes.R
import com.app.notes.databinding.FragmentHomeBinding
import com.app.notes.model.NotesModel
import com.app.notes.view.adapter.NotesAdapter
import com.app.notes.viewModel.NotesViewModel


class HomeFragment : Fragment() {


   private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: NotesViewModel
    private lateinit var filter:List<NotesModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        filter = emptyList()
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[NotesViewModel::class.java]
        // Retrieve the arguments

        // Inflate the layout for this fragment
        val fragment = AddNoteFragment()
        binding.floatingActionButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,fragment )
                .commit()
        }
        val adapter = NotesAdapter(emptyList(),requireContext(),requireActivity())
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvHome.layoutManager = gridLayoutManager
        viewModel.notes.observe(requireActivity()) { notes ->
            adapter.update(notes)
            filter = notes
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Filter the list based on the submitted query
                val filteredNotes = filter.filter { note ->
                    note.title?.lowercase()?.contains(query.toString().lowercase()) ?: false
                }
              //  Toast.makeText(requireContext(), "$filter", Toast.LENGTH_SHORT).show()
                // Update the adapter with the filtered list
                adapter.update(filteredNotes)
                return false // Return false to indicate we've handled the submit event
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Filter the list as the user types
            //    filterNotes(newText ?: "")
                val filteredNotes = filter.filter { note ->
                    note.title?.lowercase()?.contains(newText.toString().lowercase()) ?: false
                }
               // Toast.makeText(requireContext(), "$filter", Toast.LENGTH_SHORT).show()
                // Update the adapter with the filtered list
              //  Toast.makeText(requireContext(), "$filteredNotes", Toast.LENGTH_SHORT).show()
                adapter.update(filteredNotes)
                return false // Return false to indicate we've handled the change event
            }
        })
        binding.rvHome.adapter = adapter

        return binding.root
    }


}