package com.example.aasmm

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aasmm.ui.login.LoginActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_account_manager.*


class AccountManager : AppCompatActivity() {

    //    Handle user login
    private val callbackManager = CallbackManager.Factory.create()
    private lateinit var _textField: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var accessToken: AccessToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_manager)

        authFacebook()

//        Sign in or out from facebook
        fbCard.setOnClickListener {
            Snackbar.make(it, "Clicked Face", Snackbar.LENGTH_SHORT).show()

            LoginManager.getInstance()
                .logInWithReadPermissions(this, mutableListOf("public_profile"))
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
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
//                    Set card info to show the user is logged in
                    _textField = findViewById(R.id.fbTextField)
                    _textField.text = getString(R.string.message_loged_in)
                }

                override fun onCancel() {
                    _textField = findViewById(R.id.fbTextField)
                    _textField.text = getString(R.string.sign_in_to_account)
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })

        auth = FirebaseAuth.getInstance()

//        Ask the user if sure to sign out
        logoutCard.setOnClickListener {
            auth.signOut()

//            Log out of facebook account
            LoginManager.getInstance().logOut()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    //    Overrider the back button on this activity
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainLanding::class.java))
        finish()
    }
}
