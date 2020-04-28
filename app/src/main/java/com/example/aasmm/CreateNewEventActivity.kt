package com.example.aasmm

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import kotlinx.android.synthetic.main.activity_create_new_event.*
import java.text.SimpleDateFormat

import java.util.*


class CreateNewEventActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_event)

        val c = Calendar.getInstance()
        c.time = Date(System.currentTimeMillis())
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val newDate: Calendar = Calendar.getInstance()
        var startDate: Calendar
        var endDate: Calendar

        date.setOnClickListener {
            val dpd = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, newYear, newMonth, newDay ->

                    dateText.text = newYear.toString() + "-" + (newMonth + 1) + "-" + newDay
                    newDate.set(newYear, newMonth, newDay)

                },
                year,
                month,
                day
            )

            dpd.show()

        }

        startTime.setOnClickListener {
            startDate = newDate
            val timeSetListenerA = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                startDate.set(Calendar.HOUR_OF_DAY, hour)
                startDate.set(Calendar.MINUTE, minute)
                startTimeText.text = SimpleDateFormat("HH:mm").format(startDate.time)
            }
            TimePickerDialog(
                this,
                timeSetListenerA,
                startDate.get(Calendar.HOUR_OF_DAY),
                startDate.get(Calendar.MINUTE),
                true
            ).show()
        }

        endTime.setOnClickListener {
            endDate = newDate
            val timeSetListenerB = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                endDate.set(Calendar.HOUR_OF_DAY, hour)
                endDate.set(Calendar.MINUTE, minute)
                endTimeText.text = SimpleDateFormat("HH:mm").format(endDate.time)
            }
            TimePickerDialog(
                this,
                timeSetListenerB,
                endDate.get(Calendar.HOUR_OF_DAY),
                endDate.get(Calendar.MINUTE),
                true
            ).show()
        }

        fun intentHandler(startDate: Calendar, endDate: Calendar) {
            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate)
            intent.putExtra(CalendarContract.Events.ALL_DAY, false)
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate)
            intent.putExtra(CalendarContract.Events.TITLE, eventSum.text.toString())
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation.text.toString())
            intent.putExtra(CalendarContract.Events.DESCRIPTION, eventDisc.text.toString())
            startActivity(intent)
        }

        fun dateJson(): JsonAdapter<Date> {

            val moshi = Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()

            return moshi.adapter(Date::class.java)
        }

        fun dateToCalendar(date: Date): Calendar {

            var calendar = Calendar.getInstance()
            calendar.time = date
            return calendar

        }




        addEventA.setOnClickListener {

            val startDateString = dateText.text.toString() + "T" + startTimeText.text.toString()
            val endDateString = dateText.text.toString() + "T" + endTimeText.text.toString()
            val adapter = dateJson()
            val startDate = adapter.fromJson("2020-4-30T12:30")
            val endDate = adapter.fromJson("2020-4-30T12:45")
            eventDisc.setText(startDateString)
            val finalStart = startDate?.let { it1 -> dateToCalendar(it1) }
            val finalEnd = endDate?.let{ it2 -> dateToCalendar(it2)}


            if (finalStart != null && finalEnd != null) {
              intentHandler(finalStart, finalEnd)
             }

            // }

        }
    }
}
