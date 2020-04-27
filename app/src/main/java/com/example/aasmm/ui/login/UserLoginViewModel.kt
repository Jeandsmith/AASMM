package com.example.aasmm.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.aasmm.LOG_TAG
import com.example.aasmm.R
import com.example.aasmm.utilities.FileManager

//Pass to this class constructor, this app context
// View models loads to memory and manages data that has to be displayed to the user.
// The ViewModel class allow this data to survive future configurations that may happen in
//  the app lifecycle.
class UserLoginViewModel(app: Application): AndroidViewModel(app){

//    Read the file from memory when the class is initialized
    init {
        val fileContent = FileManager.getTextFromResources(app, R.raw.userpii)
        Log.i(LOG_TAG, fileContent)
    }
}