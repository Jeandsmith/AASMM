package com.example.aasmm.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aasmm.*
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var RC_SIGN_IN = 1234
    private lateinit var auth: FirebaseAuth
    private lateinit var _dialog: SignUpDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        Declarations
        auth = FirebaseAuth.getInstance()
        _dialog = SignUpDialog()

        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        // val account = GoogleSignIn.getLastSignedInAccount(this)

        login.setOnClickListener {
            if (validateForm()) {
                signIn(emailET.text.toString(), password.text.toString())
            } else {
                Snackbar.make(it, "No empty fields", Snackbar.LENGTH_LONG).show()
            }
        }

        val gSign: SignInButton = findViewById(R.id.googleSignIn)
        gSign.setSize(SignInButton.SIZE_STANDARD)
        gSign.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            // Take previous users to main landing
            val accessToken = AccessToken.getCurrentAccessToken()
            val loggedIn = accessToken != null && !accessToken.isExpired

            if (loggedIn) startActivity(Intent(this, MainLanding::class.java))
            else startActivity(Intent(this, AccountManager::class.java))
            finish()
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w(
//                FragmentActivity.TAG,
//                "signInResult:failed code=" + e.statusCode
//            )
//            updateUI(null)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
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

                val layoutInflater = layoutInflater
                val accLayout = layoutInflater.inflate(R.layout.activity_account_manager, null)
                val textView = accLayout.findViewById(R.id.accountMagTextView) as TextView

//                Take previous users to main landing
                if (loggedIn) {
                    textView.text = getString(R.string.sign_in_to_a_account)
                    startActivity(Intent(this, MainLanding::class.java))
                }

//                Take new users to the account management activity
                else {

                    textView.text = getString(R.string.add_first_account)
                    startActivity(Intent(this, AccountManager::class.java))
                }
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


