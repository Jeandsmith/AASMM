package com.example.aasmm

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_landing.*

class MainLanding : AppCompatActivity() {

    //    Declare firebase instance
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_landing)

        auth = FirebaseAuth.getInstance()

//        Dialogue to be shown to the user if no account is found on the app
        val ad = AlertDialog.Builder(this)
            .setPositiveButton(
                "Add Social Media Account",
                DialogInterface.OnClickListener { dialog, id ->
//                Send the user to the account management activity
                    startActivity(Intent(this, AccountManager::class.java))
                    finish()
                })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
//              Close the alert dialogue
                dialog?.cancel()
            })
            .setMessage("No social media account found")
            .create()

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        post_button.setOnClickListener {

//            Always check on click if the user Facebook account token has expired
            if (isLoggedIn) {
                val intent = Intent(this, CreateNewPostActivity::class.java)
                startActivity(intent)
            } else {
                ad.show()
            }
        }


        image_button.setOnClickListener {
            if (isLoggedIn) {
                val intent = Intent(this, CreateNewImagePostActivity::class.java)
                startActivity(intent)
            }
        }
        event_button.setOnClickListener {
            if (isLoggedIn) {
                val intent = Intent(this, CreateNewEventActivity::class.java)
                startActivity(intent)
            }
        }


//        Ask the user if sure to sign out
        logoutButton.setOnClickListener {
//            auth.signOut()
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
            if (isLoggedIn) {
                val intent = Intent(this, AccountManager::class.java)
                startActivity(intent)
            }
        }

    }
}
