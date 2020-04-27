@file:Suppress("DEPRECATION")

package com.example.aasmm


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.ShareButton
import kotlinx.android.synthetic.main.activity_create_new_image_post2.*

class CreateNewImagePostActivity : AppCompatActivity()/*, View.OnClickListener*/{

    private val PICK_IMAGE_REQUEST = 1234

    private var filePath: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null){

            Log.d("oar?", "yes")
            filePath = data.data;
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
            imageView!!.setImageBitmap(bitmap)

        }
    }

    private fun showFileChooser(){
        Log.d("file chooser?", "yes")
        val intent = Intent()
        intent.type="image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"SELECT PICTURE"), PICK_IMAGE_REQUEST)
    }

    private fun sharePhoto() {
        Log.d("sharePhoto?", "yes")
        if (filePath != null){
            val imageUri = filePath
            val selectedPhoto = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)

            val photo = SharePhoto.Builder()
                .setBitmap(selectedPhoto)
                .setCaption("Caption")
                .build()
            val content = SharePhotoContent.Builder()
                .addPhoto(photo)
                .build()

            Log.d("Make it to end?", "Yes")

            //val shareButton: ShareButton = findViewById(R.id.shareButton)
            //shareButton.shareContent = content

            //submitPost.setOnClickListener {
            //    shareButton.performClick()
            //}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_image_post2)

        val chooseButton = findViewById<Button>(R.id.btnChoose)
        val shareButton = findViewById<Button>(R.id.btnShare)

        chooseButton.setOnClickListener(){
            showFileChooser()
        }

        shareButton.setOnClickListener(){
            sharePhoto()
        }
    }
}




