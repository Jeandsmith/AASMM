package com.example.aasmm.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aasmm.LOGIN_ACTIVITY_TAG
import com.example.aasmm.MainLanding
import com.example.aasmm.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        setProgressBar()

        auth = FirebaseAuth.getInstance()

        login.setOnClickListener {
            if (validateForm()) {
                signIn(emailET.text.toString(), password.text.toString())
            } else {
                Snackbar.make(it, "No empty fields", Snackbar.LENGTH_LONG).show()
            }
        }
    }


    // Add a new user
    private fun CreateAccount(email: String, password: String) {
        val createUser = auth.createUserWithEmailAndPassword(email, password)

//        This context
        createUser.addOnCompleteListener(this) {

//            it = task results
            if (it.isSuccessful) {
                Log.d(LOGIN_ACTIVITY_TAG, "createUserWithEmail:Success")
                val user = auth.currentUser
//                Send this new use info to the next Activity
            } else {
                Log.w(LOGIN_ACTIVITY_TAG, "createUserWithEmail:failure", it.exception)
            }
        }
    }

    //    Sign in users
    private fun signIn(email: String, password: String) {

        val signIn = auth.signInWithEmailAndPassword(email, password)
        signIn.addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(LOGIN_ACTIVITY_TAG, "signInWithEmail;success")
                val user = auth.currentUser
                Toast.makeText(this, "Signed in", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainLanding::class.java))
                finish()
            } else {
                Log.w(LOGIN_ACTIVITY_TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(this, "Login Failed: User not found", Toast.LENGTH_SHORT).show()
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


