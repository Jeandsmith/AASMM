package com.example.aasmm

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aasmm.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        submit_sign_up.setOnClickListener {

//            TODO : This is always breaking

            Log.d(CREATE_ACCOUNT, "createUserWithEmail:failure")
            if (validateForm()) createAccount(
                fullname.text.toString(),
                userEmail.text.toString(),
                userPassowrd.text.toString()
            )
        }

    }

    private fun validateForm(): Boolean {


        var valid = true

        val full = fullname.text.toString()
        if (TextUtils.isEmpty(full)) {
            fullname.error = "Required."
            valid = false
        } else {
            fullname.error = null
        }

        val email = userEmail.text.toString()
        if (TextUtils.isEmpty(email)) {
            userEmail.error = "Required."
            valid = false
        } else {
            userEmail.error = null
        }

        val psw = userPassowrd.text.toString()
        if (TextUtils.isEmpty(psw)) {
            userPassowrd.error = "Required."
            valid = false
        } else {
            userPassowrd.error = null
        }

        return valid
    }

    // Add a new user
    private fun createAccount(userFullName: String, email: String, password: String) {
        val createUser = auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Log.d(CREATE_ACCOUNT, "createUserWithEmail:Success")
                    val user = auth.currentUser

//                [Set the info of the user]
                    val profileUpdate = UserProfileChangeRequest.Builder()
                        .setDisplayName(userFullName)
                        .build()

//              [Commit the values]
                    user?.updateProfile(profileUpdate)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "User Account Create.", Toast.LENGTH_LONG)
                                    .show()
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }
                } else {
                    Log.w(CREATE_ACCOUNT, "createUserWithEmail:failure", it.exception)
                }
            }
    }

}
