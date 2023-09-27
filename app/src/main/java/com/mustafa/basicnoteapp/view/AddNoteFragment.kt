package com.mustafa.basicnoteapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mustafa.basicnoteapp.R
import com.mustafa.basicnoteapp.databinding.FragmentAddNoteBinding
import com.mustafa.basicnoteapp.roomdb.Note
import com.mustafa.basicnoteapp.util.Status
import com.mustafa.basicnoteapp.viewmodel.AddNoteViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNoteFragment : Fragment(R.layout.fragment_add_note) {

    private var fragmentBinding : FragmentAddNoteBinding ?= null
    lateinit var viewModel : AddNoteViewModel
    private var noteUuid : Int ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentAddNoteBinding.bind(view)
        fragmentBinding = binding

        arguments?.let {
            noteUuid = AddNoteFragmentArgs.fromBundle(it).uuid
        }

        viewModel = ViewModelProvider(requireActivity())[AddNoteViewModel::class.java]

        observeNote()
        binding.addNote.setOnClickListener {
            if(noteUuid == -1){
                viewModel.makeNote(binding.titleEditText.text.toString() , binding.noteEditText.text.toString())
            }
            else{
                viewModel.updateOldNote(binding.titleEditText.text.toString() , binding.noteEditText.text.toString() , noteUuid!!)
            }
        }
        subscribeToObserver()
    }

    private fun subscribeToObserver(){
        viewModel.insertNoteMassage.observe(viewLifecycleOwner , Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext() , "Success" , Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertNoteMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext() , it.message , Toast.LENGTH_LONG).show()
                    //viewModel.resetInsertNoteMsg()
                }

                Status.LOADING -> {}
            }
        })
    }

    private fun observeNote() {
        noteUuid?.let { uuid ->
            viewModel.getNoteWithId(uuid)
            viewModel.notes.observe(viewLifecycleOwner, Observer { notes ->
                val note = notes.find { it.uuid == uuid }
                note?.let {
                    fragmentBinding?.titleEditText?.setText(it.title)
                    fragmentBinding?.noteEditText?.setText(it.note)
                }
            })
        }
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}