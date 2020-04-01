package com.example.aasmm

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateNewPostActivity : AppCompatActivity() {

    private var newPost: EditText = findViewById(R.id.writeNewPost)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)

        if (newPost.text != null){

        }

    }
}
