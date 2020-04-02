package com.example.aasmm.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aasmm.R

class LoginActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        UserLoginViewModel(application)
    }

}
