package com.example.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.adapter.ListAdapter
import com.example.noteapp.database.UserData
import com.example.noteapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_main_screen_dash.view.*

class MainScreenDash : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main_screen_dash, container, false)

        //Recycle view
        val adapter = ListAdapter()
        val recyclerView = view.recycleview
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )  //LinearLayoutManager(requireContext())

        //User View Model
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        //floatingAddButton direct to add note screen
        view.floatingAddButton.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_mainScreenDash_to_addScreen2)
        }

        return view
    }



}