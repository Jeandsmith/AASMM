// TODO: This has to be optimized
// Use picasso

@file:Suppress("DEPRECATION")

package com.example.aasmm


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.facebook.share.model.SharePhoto
import com.facebook.share.model.SharePhotoContent
import com.facebook.share.widget.MessageDialog
import com.facebook.share.widget.ShareButton
import kotlinx.android.synthetic.main.activity_create_new_image_post.*
import java.io.IOException

class CreateNewImagePostActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1234

    private var filePath: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data

            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                btnChoose!!.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), PICK_IMAGE_REQUEST)
    }

    private lateinit var photo: SharePhoto
    private lateinit var bitmap: Bitmap
    private lateinit var _quote: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_image_post)

        bitmap = BitmapFactory.decodeResource(resources, R.drawable.facebook)

        btnChoose.setOnClickListener {
            showFileChooser()
        }

        btnShare.setOnClickListener {
            shareImage()
        }

    }

    private fun shareImage() {

        val imageCaption = findViewById<EditText>(R.id.quoteText)

        photo = if (imageCaption.text == null) {
            SharePhoto.Builder()
                .setBitmap(bitmap)
                .build()
        } else {
            SharePhoto.Builder()
                .setBitmap(bitmap)
                .setCaption(imageCaption.text.toString())
                .build()
        }

        val sharePhoto = SharePhotoContent.Builder()
            .addPhoto(photo)
            .build()

        MessageDialog.show(this, sharePhoto)

        val photoShare = findViewById<ShareButton>(R.id.photoShare)
        photoShare.shareContent = sharePhoto
        photoShare.performClick()

    }
}