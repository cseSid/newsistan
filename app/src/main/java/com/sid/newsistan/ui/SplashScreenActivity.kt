package com.sid.newsistan.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sid.newsistan.MainActivity
import com.sid.newsistan.R
import com.sid.newsistan.room.RoomDB

class SplashScreenActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RoomDB.getInstance(this)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splashscreen)


        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable { // Do something after 5s = 5000ms
            val intent = Intent(this, EntryScreenActivity::class.java)
            startActivity(intent)
        }, 1000)


    }
}