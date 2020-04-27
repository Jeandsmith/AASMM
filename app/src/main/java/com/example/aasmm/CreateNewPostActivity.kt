package com.example.aasmm


import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareButton
import kotlinx.android.synthetic.main.activity_create_new_post.*


class CreateNewPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post)

//        val shareDi = ShareDialog(this)

        val content = ShareLinkContent.Builder()
            .setQuote("Very nice link")
            .setContentUrl(Uri.parse("https://youtube.com"))
            .build()


//        if (shareDi.canShow(content)) shareDi.show(content)
        val shareButton: ShareButton = findViewById(R.id.shareButton)
        shareButton.shareContent = content

        submitPost.setOnClickListener {
            shareButton.performClick()
        }
    }
}
