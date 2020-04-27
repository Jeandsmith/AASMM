package com.example.aasmm;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;

public class Homepage extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        /* fetching the username from LoginActivity */
        String username = getIntent().getStringExtra("username");
        TextView uname = findViewById(R.id.TV_username);
        uname.setText(username);
    }
}
