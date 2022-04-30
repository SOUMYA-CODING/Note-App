package com.example.noteapp.startup

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.noteapp.R
import com.example.noteapp.mainsection.DashboardPage

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        //Splash Screen
        Handler(Looper.getMainLooper()).postDelayed({
            sharedPreferences = getSharedPreferences("SharePred", MODE_PRIVATE)
            val isFirstTime: Boolean = sharedPreferences.getBoolean("firstTime", true)

            if (isFirstTime) {
                val editor = sharedPreferences.edit()
                editor.commit()

                val intent = Intent(this, DashboardPage::class.java)
                startActivity(intent)
                finish()

            } else {
                val intent = Intent(this, WelcomePage::class.java)
                startActivity(intent)
                finish()
            }
        }, 1000)
    }
}