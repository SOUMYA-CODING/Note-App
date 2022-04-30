package com.example.noteapp.startup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noteapp.R
import com.example.noteapp.mainsection.DashboardPage
import kotlinx.android.synthetic.main.activity_welcome_page.*

class WelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        //call the functions
        welomepagefun()
    }

    private fun welomepagefun() {
        welcomepagebtn.setOnClickListener {
            val intent = Intent(this, DashboardPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}