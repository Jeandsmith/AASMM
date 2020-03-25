package com.example.aasmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_create_new_post.*

class CreateNewPostActivity : AppCompatActivity() {

    private lateinit var list_view: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)

        val arr = mutableListOf<String>("Facebook", "Twitter")
        list_view = findViewById(R.id.rec_id)
//        var arr_adapter = ArrayAdapter<String>(this, R.layout.activity_create_new_post, R.id.textView, arr)
//        list_view.adapter()
    }
}
