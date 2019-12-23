package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // TextView
        textView = findViewById(R.id.text_N5);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Send an intent to open up the second Activity
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("level",5);
                startActivity(intent);
            }
        });
    }
}
