package com.example.aasmm


import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareButton
import kotlinx.android.synthetic.main.activity_create_new_post.*


class CreateNewPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)

        submitPost.setOnClickListener {
            makePost()
        }
    }

    private lateinit var _quote: EditText
    private lateinit var link: EditText
    private lateinit var content: ShareLinkContent

    private fun makePost() {

        //        Create share interface
        _quote = findViewById(R.id.writeNewPost)
        link = findViewById(R.id.writeLink)
//        messageDialog = findViewById(R.id.writeNewPost)


        if (_quote.text.toString().isEmpty() && link.text.toString().isEmpty()) {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("The fields need to have information.")
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }).create()
                .show()
        } else {
            content = ShareLinkContent.Builder()
                .setQuote(_quote.text.toString())
                .setContentUrl(Uri.parse(link.text.toString()))
                .build()

            val shareButton: ShareButton = findViewById(R.id.shareButton)
            shareButton.shareContent = content

            shareButton.performClick()

        }
    }
}
