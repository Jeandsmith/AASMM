package com.example.aasmm.utilities

import android.content.Context

class FileManager {

//    Equivalent to static method in C#. Is attached to the class. Not an instance of the class
    companion object {

//    To be able to get the file from resources, the application context is needed.
        fun getTextFromResources(context: Context, resourceId: Int): String{

//            The id of every resource is an int.
            context.resources.openRawResource(resourceId).use {

                it.bufferedReader().use {
                    return it.readText()
                }
            }

        }

    }
}