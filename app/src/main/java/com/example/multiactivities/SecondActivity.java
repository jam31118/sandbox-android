package com.example.multiactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // When the home button (or 'up' button) is pressed,
        // this activity is closed and go back to it parent activity defined in the manifest file
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) { throw new NullPointerException(); }
        else { actionBar.setDisplayHomeAsUpEnabled(true); }
    }
}
