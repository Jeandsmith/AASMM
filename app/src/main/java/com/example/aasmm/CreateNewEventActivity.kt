package com.example.aasmm

import android.Manifest
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentResolver
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import kotlinx.android.synthetic.main.activity_create_new_event.*
import java.text.SimpleDateFormat

import java.util.*


class CreateNewEventActivity : AppCompatActivity() {

    private val TAG = "PermissionDemo"
    private val RECORD_REQUEST_CODE = 100

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.RECORD_AUDIO)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.WRITE_CALENDAR),
            RECORD_REQUEST_CODE)
    }



@RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_event)
    //        Dialogue to be shown to the user if date and time fields are incomplete
    val noDate = AlertDialog.Builder(this)
        .setPositiveButton(
            "Return",
            DialogInterface.OnClickListener { dialog, id ->
                dialog?.cancel()
            })
        .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
//              Sends User back to Main Landing
            startActivity(Intent(this, MainLanding::class.java))
            finish()
        })
        .setMessage("Please Set Date & Time")
        .create()

            //        Dialogue to be shown to the user if data is sent to be added to calendar
            val success = AlertDialog.Builder(this)
        .setPositiveButton(
            "Return to Main Landing",
            DialogInterface.OnClickListener { dialog, id ->
//                Send the user back to main landing
                startActivity(Intent(this, MainLanding::class.java))
                finish()
            })
        .setNegativeButton("Add New Event", DialogInterface.OnClickListener { dialog, id ->
            startActivity(Intent(this, CreateNewEventActivity::class.java))
            finish()
        })
        .setMessage("Success!")
        .create()

        setupPermissions()

    //Initializing fields for time and date pickers
        val c = Calendar.getInstance()
        c.time = Date(System.currentTimeMillis())
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val newDate: Calendar = Calendar.getInstance()

        //Date Picker Button - Allows user to select a date
        date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, newYear, newMonth, newDay ->
                    if(newMonth < 10)
                        dateText.setText(newYear.toString() + "-0" + (newMonth + 1) + "-" + newDay)
                    else
                        dateText.setText(newYear.toString() + "-" + (newMonth + 1) + "-" + newDay)
                    newDate.set(newYear, newMonth, newDay)

                },
                year,
                month,
                day
            )

            dpd.show()

        }

    //Time Picker Button used to pick the start time
        startTime.setOnClickListener {
            val timeSetListenerA = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                newDate.set(Calendar.HOUR_OF_DAY, hour)
                newDate.set(Calendar.MINUTE, minute)
                startTimeText.setText(SimpleDateFormat("HH:mm").format(newDate.time))
            }
            TimePickerDialog(
                this,
                timeSetListenerA,
                newDate.get(Calendar.HOUR_OF_DAY),
                newDate.get(Calendar.MINUTE),
                false
            ).show()


        }

        //Time Picker Button used to pick the end time
        endTime.setOnClickListener {
            val timeSetListenerB = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                newDate.set(Calendar.HOUR_OF_DAY, hour)
                newDate.set(Calendar.MINUTE, minute)
                endTimeText.setText(SimpleDateFormat("HH:mm").format(newDate.time))
            }
            TimePickerDialog(
                this,
                timeSetListenerB,
                newDate.get(Calendar.HOUR_OF_DAY),
                newDate.get(Calendar.MINUTE),
                true
            ).show()
        }

        //Function that placed the event into the calendar
        fun eventHandler(begin: Long, end: Long) {
            val intent = Intent(Intent.ACTION_INSERT).apply {
                data = CalendarContract.Events.CONTENT_URI
                putExtra(CalendarContract.Events.TITLE, eventSum.text.toString())
                putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation.text.toString())
                putExtra(CalendarContract.Events.DESCRIPTION, eventDisc.text.toString())
                putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }



            fun dateToCalendar(date: Date): Calendar {

                var calendar = Calendar.getInstance()
                calendar.time = date
                return calendar

            }



        //Main add event button
            addEventA.setOnClickListener {


                if(dateText.text.toString().length < 10 || startTimeText.text.toString().length > 5 ||endTimeText.text.toString().length > 5)
                    noDate.show()
                else {
                    val year = dateText.text.toString().substring(0..3).toInt()
                    val month = dateText.text.toString().substring(5..6).toInt() - 1
                    val day = dateText.text.toString().substring(8..9).toInt()
                    val startHour = startTimeText.text.toString().substring(0..1).toInt()
                    val endHour = endTimeText.text.toString().substring(0..1).toInt()
                    val startMin = startTimeText.text.toString().substring(3..4).toInt()
                    val endMin = endTimeText.text.toString().substring(3..4).toInt()

                    val finalStart: Long = Calendar.getInstance().run {
                        set(year, month, day, startHour, startMin)
                        timeInMillis
                    }
                    val finalEnd: Long = Calendar.getInstance().run {
                        set(year, month, day, endHour, endMin)
                        timeInMillis
                    }

                    eventHandler(finalStart, finalEnd)

                    success.show()
                }


                 }

            }
        }
