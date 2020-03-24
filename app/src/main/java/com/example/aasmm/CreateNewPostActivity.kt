package com.example.aasmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_create_new_post.*

class CreateNewPostActivity : AppCompatActivity() {

    private lateinit var ListView: list_view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)



    }
}
