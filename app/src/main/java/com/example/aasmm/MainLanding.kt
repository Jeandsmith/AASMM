package com.example.aasmm

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainLanding : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_landing)

        val textButton = findViewById<ImageButton>(R.id.text)
        val imageButton = findViewById<ImageButton>(R.id.image)
        val eventButton = findViewById<ImageButton>(R.id.event)

        textButton.setOnClickListener() {
            openTextActivity()
        }
        imageButton.setOnClickListener() {
            openImageActivity()
        }
        eventButton.setOnClickListener() {
            openEventActivity()
        }
    }

    fun openTextActivity(){
        val myIntent = Intent(this, CreateNewPostActivity::class.java)
        startActivity(myIntent)
    }

    fun openImageActivity(){
        val myIntent = Intent(this, CreateNewImagePostActivity::class.java)
        startActivity(myIntent)
    }

    fun openEventActivity(){
        val myIntent = Intent(this, CreateNewEventActivity::class.java)
        startActivity(myIntent)
    }
}
