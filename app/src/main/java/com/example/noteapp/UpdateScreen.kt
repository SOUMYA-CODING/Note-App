package com.example.noteapp

import android.app.AlertDialog
import android.os.Bundle
import com.example.noteapp.database.UserData
import android.text.TextUtils
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.noteapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_screen.view.*
import kotlinx.android.synthetic.main.fragment_update_screen.*
import kotlinx.android.synthetic.main.fragment_update_screen.view.*
import java.util.*

class UpdateScreen : Fragment() {

    private val args by navArgs<UpdateScreenArgs>()

    private lateinit var mUserViewMode: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_screen, container, false)

        mUserViewMode = ViewModelProvider(this).get(UserViewModel::class.java)

        //fetch and set the data
        view.txt_titleUpdate.setText(args.currentUser.titletxt)
        view.txt_datetimeUpdate.setText(args.currentUser.datetxt)
        view.txt_bodyUpdate.setText(args.currentUser.notetxt)

        //function to delete record
        view.deletebtn.setOnClickListener {
            deleteItem()
        }

        //cal the update button
        view.updateBtn.setOnClickListener {
            updateItem()
        }

        //back btn direct to mainscreen
        view.backBtnUpdate.setOnClickListener {
            Navigation.findNavController(view!!)
                .navigate(R.id.action_updateScreen_to_mainScreenDash)
        }

        return view
    }

    private fun deleteItem() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewMode.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
            //navigate to main screen
            Navigation.findNavController(requireView())
                .navigate(R.id.action_updateScreen_to_mainScreenDash)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete !")
        builder.create().show()
    }

    private fun updateItem() {
        val title = txt_titleUpdate.text.toString()
        val date = txt_datetimeUpdate.text.toString()
        val notebody = txt_bodyUpdate.text.toString()

        if (inputCheck(title, date, notebody)) {
            //create user object
            val userUpdate = UserData(args.currentUser.id, title, date, notebody)

            //Update current data
            mUserViewMode.updateUser(userUpdate)

            //navigate to main screen
            Navigation.findNavController(requireView())
                .navigate(R.id.action_updateScreen_to_mainScreenDash)
        } else {
            Toast.makeText(requireContext(), "Error ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, date: String, notebody: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(date) && TextUtils.isEmpty(notebody))
    }

}