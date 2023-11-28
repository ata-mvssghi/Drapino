package com.example.drapino

import android.content.Intent
import androidx.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class EntranceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val check  = preferences.getString("user_token","false")
        if(!check.equals("false")){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        setContentView(R.layout.activity_entrance)
    }
}