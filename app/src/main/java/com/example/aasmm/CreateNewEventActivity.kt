package com.example.aasmm


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.EventDateTime
import kotlinx.android.synthetic.main.activity_create_new_event.*
import java.util.*
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.HttpRequestInitializer
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Events;
import org.w3c.dom.DOMStringList

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


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
        calendarView.visibility = INVISIBLE
        timePicker1.visibility = INVISIBLE

        timePicker1.setIs24HourView(true)

        calendarView.setDate(System.currentTimeMillis(), false, true)

        var startDate = Date(calendarView.date)
        var endDate = Date(calendarView.date)

        date.setOnClickListener{
            if (calendarView.visibility == INVISIBLE){
                eventSum.visibility = INVISIBLE
                eventDisc.visibility = INVISIBLE
                eventLocation.visibility = INVISIBLE
                startTime.visibility = INVISIBLE
                endTime.visibility = INVISIBLE
                calendarView.visibility  = VISIBLE
            }
            else{
                eventSum.visibility = VISIBLE
                eventDisc.visibility = VISIBLE
                eventLocation.visibility = VISIBLE
                calendarView.visibility  = INVISIBLE
                startTime.visibility = VISIBLE
                endTime.visibility = VISIBLE

                startDate = Date(calendarView.date)
                endDate = Date(calendarView.date)

                date.text = startDate.toString()

            }
        }



        startTime.setOnClickListener{
            if (calendarView.visibility == INVISIBLE){
                eventSum.visibility = INVISIBLE
                eventDisc.visibility = INVISIBLE
                eventLocation.visibility = INVISIBLE
                timePicker1.visibility  = VISIBLE
                date.visibility = INVISIBLE
                endTime.visibility = INVISIBLE
            }
            else{
                eventSum.visibility = VISIBLE
                eventDisc.visibility = VISIBLE
                eventLocation.visibility = VISIBLE
                timePicker1.visibility  = INVISIBLE
                date.visibility = VISIBLE
                endTime.visibility = VISIBLE



                startTime.text = timePicker1.toString()
                startDate = Date(calendarView.date)
                endDate = Date(calendarView.date)

                startDate.hours = timePicker1.hour
                startDate.minutes = timePicker1.minute



            }
        }

        startTime.setOnClickListener{
            if (calendarView.visibility == INVISIBLE){
                eventSum.visibility = INVISIBLE
                eventDisc.visibility = INVISIBLE
                eventLocation.visibility = INVISIBLE
                timePicker1.visibility  = VISIBLE
                date.visibility = INVISIBLE
                startTime.visibility = INVISIBLE
            }
            else{
                eventSum.visibility = VISIBLE
                eventDisc.visibility = VISIBLE
                eventLocation.visibility = VISIBLE
                timePicker1.visibility  = INVISIBLE
                date.visibility = VISIBLE
                startTime.visibility = VISIBLE



                startTime.text = timePicker1.toString()
                startDate = Date(calendarView.date)
                endDate = Date(calendarView.date)

                endDate.hours = timePicker1.hour
                endDate.minutes = timePicker1.minute



            }
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

        var intent = Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", startDate);
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", endDate);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent)
        val calendarId = "primary"

        //var service = Build("calendar")

        //Event = service.events.insert(calendarId, Event).execute()
        //System.out.printf("Event created: %s\n", Event.getHtmlLink())

            }

    }

}
