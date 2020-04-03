package com.example.aasmm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create_new_post.*

class CreateNewPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)

//        if (newPost.text != null){
//
//        }

        submitPost.setOnClickListener {
            Snackbar.make(it, "Post submitted", Snackbar.LENGTH_SHORT).show()
        }

    }
}
