package com.example.aasmm.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aasmm.*
import com.facebook.AccessToken
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var _dialog: SignUpDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        Declarations
        auth = FirebaseAuth.getInstance()
        _dialog = SignUpDialog()

        login.setOnClickListener {
            if (validateForm()) {
                signIn(emailET.text.toString(), password.text.toString())
            } else {
                Snackbar.make(it, "No empty fields", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    //    Sign in users
    private fun signIn(email: String, password: String) {

        val signIn = auth.signInWithEmailAndPassword(email, password)
        signIn.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
//                The user is found
                Log.d(LOGIN_ACTIVITY_TAG, "signInWithEmail;success")

                Toast.makeText(
                    this,
                    "Welcome ${auth.currentUser}",
                    Toast.LENGTH_LONG
                )
                    .show()

                val accessToken = AccessToken.getCurrentAccessToken()
                val loggedIn = accessToken != null && !accessToken.isExpired

//                Take previous users to main landing
                if (loggedIn) startActivity(Intent(this, MainLanding::class.java))

//                Take new users to the account management activity
                else startActivity(Intent(this, AccountManager::class.java))
                finish()
            } else {
//                The user is not found
                Log.w(LOGIN_ACTIVITY_TAG, "signInWithEmail:failure", task.exception)
                _dialog.show(supportFragmentManager, "SIGN_UP_DIALOG")
            }
        }

    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = emailET.text.toString()
        if (TextUtils.isEmpty(email)) {
            emailET.error = "Required."
            valid = false
        } else {
            emailET.error = null
        }

        val psw = password.text.toString()
        if (TextUtils.isEmpty(psw)) {
            password.error = "Required."
            valid = false
        } else {
            password.error = null
        }

        return valid
    }

}


