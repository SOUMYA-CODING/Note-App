package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.MainScreenDashDirections
import com.example.noteapp.R
import com.example.noteapp.database.UserData
import kotlinx.android.synthetic.main.list_structure.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<UserData>()

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_structure, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        //for showing data
        holder.itemView.title.text = currentItem.titletxt
        holder.itemView.notebody.text = currentItem.notetxt
        holder.itemView.datetime.text = currentItem.datetxt

        holder.itemView.rowLayout.setOnClickListener {
            val action = MainScreenDashDirections.actionMainScreenDashToUpdateScreen(currentItem)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun setData(user: List<UserData>) {
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

