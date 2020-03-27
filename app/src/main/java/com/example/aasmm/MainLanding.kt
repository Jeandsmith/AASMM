package com.example.aasmm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_landing.*

class MainLanding : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_landing)

        post_button.setOnClickListener{
            val intent = Intent(this, CreateNewPostActivity::class.java)
            startActivity(intent)
//            Because the user might want to return to the main landing, we cannot release the activity
        }
        image_button.setOnClickListener {
            val intent = Intent(this, CreateNewImagePostActivity::class.java)
            startActivity(intent)
//            Because the user might want to return to the main landing, we cannot release the activity
        }
        event_button.setOnClickListener {
            val intent = Intent(this, CreateNewEventActivity::class.java)
            startActivity(intent)
//            Because the user might want to return to the main landing, we cannot release the activity
        }

    }
}
