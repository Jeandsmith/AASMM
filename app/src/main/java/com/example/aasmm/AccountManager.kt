package com.example.aasmm

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_account_manager.*


class AccountManager : AppCompatActivity() {

    //    Handle user login
    private val callbackManager = CallbackManager.Factory.create()
    private lateinit var _textField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_manager)

        val accessToken = AccessToken.getCurrentAccessToken()
        val loggedIn = accessToken != null && !accessToken.isExpired

        if (!loggedIn){
            fbCard.setOnClickListener {
                Snackbar.make(it, "Clicked Face", Snackbar.LENGTH_SHORT).show()
                authFacebook()
            }

            _textField = findViewById(R.id.fbTextField)
            _textField.text = getString(R.string.message_loged_in)
        }
    }

    //    Get the login results
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    //    Auth facebook login
    private fun authFacebook() {

//        Handle the facebook user login
//        LoginManager.getInstance().registerCallback(callbackManager,
//            object : FacebookCallback<LoginResult?> {
//                override fun onSuccess(loginResult: LoginResult?) {
//                   // code
//                }
//
//                override fun onCancel() {
//                    // App code
//                }
//
//                override fun onError(exception: FacebookException) {
//                    // App code
//                }
//            })
        val loginButton = findViewById<LoginButton>(R.id.login_button)
        loginButton.setPermissions(mutableListOf("email"))
        loginButton.registerCallback(callbackManager, object: FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                // App code
            }

            override fun onCancel() {
                // App code
            }

            override fun onError(exception: FacebookException?) {
                // App code
            }
        })
    }

//    Overrider the back button on this activity
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainLanding::class.java))
        finish()
    }
}
