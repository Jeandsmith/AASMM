@file:Suppress("DEPRECATION")

package com.example.aasmm


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import kotlinx.android.synthetic.main.activity_create_new_image_post2.*

class CreateNewImagePostActivity : AppCompatActivity(), View.OnClickListener{

    private val PICK_IMAGE_REQUEST = 1234

    override fun onClick(v: View?) {
        val intent = Intent()
        intent.type="image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"SELECT PICTURE"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null){

            val imageUri = data.data
            val selectedPhoto = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)

            val photo = SharePhoto.Builder()
                .setBitmap(selectedPhoto)
                .setCaption("Caption")
                .build()
            val content = SharePhotoContent.Builder()
                .addPhoto(photo)
                .build()

        }
    }

    private fun showFileChooser(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_image_post2)

        btnChoose.setOnClickListener(this)
    }
}




