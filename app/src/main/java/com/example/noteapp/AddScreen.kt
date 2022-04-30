package com.example.noteapp


import android.os.Bundle
import android.text.TextUtils
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.noteapp.database.UserData
import com.example.noteapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_screen.*
import kotlinx.android.synthetic.main.fragment_add_screen.view.*
import java.util.*

class AddScreen : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_screen, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //set the time text to datetime
        val d = Date()
        val setdate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        view.txt_datetime.text = setdate

        //backbtn direct to mainscreen
        view.backBtnAdd.setOnClickListener {
            Navigation.findNavController(view!!).navigate(R.id.action_addScreen_to_mainScreenDash2)
        }

        //add record
        view.doneBtnAdd.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        //get the datas
        val t_title = txt_title.text.toString()
        val t_datetime = txt_datetime.text.toString()
        val t_txtbody = txt_body.text.toString()

        if (inputCheck(t_title, t_datetime, t_txtbody)) {
            val user = UserData(0, t_title, t_datetime, t_txtbody)

            //user to database
            mUserViewModel.addUser(user)
            //show message after added
            Toast.makeText(requireContext(), "Successfully ", Toast.LENGTH_SHORT).show()
            //navigate to mainscreen
            Navigation.findNavController(requireView())
                .navigate(R.id.action_addScreen_to_mainScreenDash2)
        } else {
            Toast.makeText(requireContext(), "Error ", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(t_title: String, t_datetime: String, t_body: String): Boolean {
        return !(TextUtils.isEmpty(t_title) && TextUtils.isEmpty(t_datetime) && TextUtils.isEmpty(
            t_body
        ))
    }
}