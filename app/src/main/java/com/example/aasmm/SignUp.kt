package com.example.aasmm

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aasmm.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        submit_sign_up.setOnClickListener {

//            TODO : This is always breaking

            if (validateForm()) {
                createAccount()
            }
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
    private fun createAccount() {
        auth.createUserWithEmailAndPassword(userEmail.text.toString(), userPassowrd.text.toString())
            .addOnCompleteListener(this) {

                Log.d(CREATE_ACCOUNT, "Creating Account")

                if (it.isSuccessful) {
                    Log.d(CREATE_ACCOUNT, "createUserWithEmail:Success")
                    val user = auth.currentUser

//                  [Set the info of the user]
                    val profileUpdate = UserProfileChangeRequest.Builder()
                        .setDisplayName(fullname.text.toString())
                        .build()

//                  [Commit the values]
                    user?.updateProfile(profileUpdate)
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "User Account Create.\n Welcome ${auth.currentUser}", Toast.LENGTH_LONG)
                                    .show()
                                startActivity(Intent(this, MainLanding::class.java))
                                finish()
                            }
                        }
                } else {
                    Log.w(CREATE_ACCOUNT, "createUserWithEmail:failure", it.exception)
                    Toast.makeText(this, "Unable to create account. ${userEmail.text} not valid", Toast.LENGTH_LONG)
                        .show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
    }

}
