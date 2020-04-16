@file:Suppress("DEPRECATION")

package com.example.aasmm


import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_create_new_image_post2.*
import java.io.IOException

class CreateNewImagePostActivity : AppCompatActivity(), View.OnClickListener{

    private val PICK_IMAGE_REQUEST = 1234

    private var filePath:Uri? = null

    //internal var storage:FirebaseStorage?=null
    //internal var storageReference:StorageReference?=null

    override fun onClick(v: View?) {
        if(v === btnChoose)
            showFileChooser()
        else if(v === btnUpload)
            uploadFile()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            filePath = data.data
            try{
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
                imageView!!.setImageBitmap(bitmap)
            }catch (e:IOException)
            {
                e.printStackTrace()
            }
        }
    }

    private fun uploadFile(){

    }

    private fun showFileChooser(){
        val intent = Intent()
        intent.type="image/*"
        intent.action= Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"SELECT PICTURE"), PICK_IMAGE_REQUEST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_image_post2)


        //Initilize Firebase
        //storage = FirebaseStorage.getInstance()
        //storageReference = storage!!.reference

        btnChoose.setOnClickListener(this)
    }
}