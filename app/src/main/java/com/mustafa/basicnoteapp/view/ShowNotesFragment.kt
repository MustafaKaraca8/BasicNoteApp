package com.mustafa.basicnoteapp.view

import android.graphics.drawable.GradientDrawable.Orientation
import android.os.Bundle
import android.view.View
import android.widget.GridLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mustafa.basicnoteapp.R
import com.mustafa.basicnoteapp.adapter.RecyclerViewAdapter
import com.mustafa.basicnoteapp.databinding.FragmentShowNotesBinding
import com.mustafa.basicnoteapp.viewmodel.ShowNotesViewModel


class ShowNotesFragment : Fragment(R.layout.fragment_show_notes) {

    private var fragmentBinding : FragmentShowNotesBinding ?= null

    private lateinit var recyclerViewAdapter : RecyclerViewAdapter
    private lateinit var recyclerView : RecyclerView
    lateinit var viewModel : ShowNotesViewModel

    private val swipeCallback = object :ItemTouchHelper.SimpleCallback(0 ,ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPos = viewHolder.layoutPosition
            val selectedNote = recyclerViewAdapter.noteList[layoutPos]
            viewModel.deleteNote(selectedNote)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentShowNotesBinding.bind(view)
        fragmentBinding = binding

        viewModel = ViewModelProvider(requireActivity())[ShowNotesViewModel::class.java]
        subscribeToObserver()

        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView = binding.feedRecyclerView
        val layoutManager = GridLayoutManager(requireContext(),2)
        layoutManager.isUsingSpansToEstimateScrollbarDimensions = false
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewAdapter
        ItemTouchHelper(swipeCallback).attachToRecyclerView(recyclerView)

        binding.moveAddNote.setOnClickListener {
            val action = ShowNotesFragmentDirections.actionShowNotesFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }
    }

    private fun subscribeToObserver(){
        viewModel.notes.observe(viewLifecycleOwner , Observer {
            recyclerViewAdapter.noteList = it
        })
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}