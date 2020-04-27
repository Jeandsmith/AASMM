package com.example.aasmm

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpDialog.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpDialog : DialogFragment() {

    //    What to do while the dialog fragment is being created
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.sign_up_message)
                .setPositiveButton(R.string.yes_sign_up,
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(it, SignUp::class.java)
                        startActivity(intent)
                        dialog.dismiss()
                    })
                .setNegativeButton(R.string.cancel_sign_up,
                    DialogInterface.OnClickListener { dialog, id ->

                        getDialog()?.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
