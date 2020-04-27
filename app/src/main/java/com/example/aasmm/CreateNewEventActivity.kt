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
/*
        class CalendarQuickStart {
            val APPLICATION_NAME = "Google Calendar API Java Quickstart"
            val JSON_FACTORY = JacksonFactory.getDefaultInstance()
            val TOKENS_DIRECTORY_PATH = "tokens"

            val SCOPES = Collections.singletonList(CalendarScopes.CALENDAR)

            val CREDENTIALS_FILE_PATH = "/credentials.json"

            private fun getCredentials(HTTP_TRANSPORT: NetHttpTransport) {
                // Load client secrets.
                var in = CalendarQuickStart.class. getResourceAsStream (CREDENTIALS_FILE_PATH);
                if ( in == null) run {
                    throw FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
                }
            }

                val clientSecrets =
                    GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(System.`in`))

                val flow = GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                    .setDataStoreFactory( FileDataStoreFactory(java.io.File(TOKENS_DIRECTORY_PATH)))
                    .setAccessType("offline")
                    .build();
                val receiver = LocalServerReceiver.Builder().setPort(8888).build();
                return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
            }


        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()

              var service = Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

*/
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

                    dateText.setText(newYear.toString() + "-" + (newMonth + 1) + "-" + newDay)
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
                startTimeText.setText(SimpleDateFormat("HH:mm").format(startDate.time))
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
                endTimeText.setText(SimpleDateFormat("HH:mm").format(endDate.time))
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
            val intent = Intent(Intent.ACTION_EDIT);
            intent.type = "vnd.android.cursor.item/event";
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDate);
            intent.putExtra(CalendarContract.Events.ALL_DAY, false);
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDate);
            intent.putExtra(CalendarContract.Events.TITLE, eventSum.text.toString());
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation.text.toString())
            intent.putExtra(CalendarContract.Events.DESCRIPTION, eventDisc.text.toString())
            startActivity(intent)
        }

        fun dateJson(): JsonAdapter<Date> {
            /*val customDateAdapter: Any = object : Any() {
                var dateFormat: DateFormat? = null

                @ToJson
                @Synchronized
                fun dateToJson(d: Date?): String? {
                    return dateFormat?.format(d)
                }

                @FromJson
                @Synchronized
                @Throws(ParseException::class)
                fun dateToJson(s: String?): Date? {
                    return dateFormat?.parse(s)
                }

                init {
                    dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
                    (dateFormat as SimpleDateFormat).timeZone = TimeZone.getTimeZone("EST")
                }
            }
            */



            val moshi = Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()

            return moshi.adapter(Date::class.java)
        }

        fun dateToCalendar(date: Date): Calendar {

            var calendar = Calendar.getInstance();
            calendar.time = date;
            return calendar;

        }




        addEventA.setOnClickListener {

            /*
                var Event = Event()
                    .setSummary(eventSum.text.toString())
                    .setLocation(eventLocation.text.toString())
                    .setDescription(eventDisc.text.toString())
                val start = DateTime(startDate)
                val end = DateTime(startDate)
                var startEvent = EventDateTime()
                    .setDateTime(start)
                var endEvent = EventDateTime()
                    .setDateTime(end)

                Event.setStart(startEvent)
                Event.setEnd(endEvent)

         */

            //val dateAdapter: JsonAdapter<Date> = dateMoshi.adapter(Date::class.java)
            //if(dateAdapter.fromJson("\"1985-04-12T23:20\"")!!.equals(Date(1985, 4, 12, 23, 20)))
            //    eventDisc.setText("it worked")
            // else
            //  eventDisc.setText(("\"" + dateText.text.toString() + "T" + 23 + ":" + 20 + "\""))

            //var finalDate = dateAdapter.fromJson("\"2020-04-30T23:20\"")


            //val calendarId = "primary"


            //var service = Build("calendar")

            //Event = service.events.insert(calendarId, Event).execute()
            //System.out.printf("Event created: %s\n", Event.getHtmlLink())
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
