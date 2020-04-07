package com.example.aasmm


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_create_new_image_post.*

class CreateNewImagePostActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_image_post)

        val image = findViewById<ImageView>(R.id.imageToUpload)
        val uploadButton = findViewById<Button>(R.id.uploadImage)
        val caption = findViewById<EditText>(R.id.caption)

        imageToUpload.setOnClickListener(){
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivity(galleryIntent)
        }
        uploadButton.setOnClickListener(){

        }

    }
}



