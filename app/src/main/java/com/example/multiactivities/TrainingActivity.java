package com.example.multiactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class TrainingActivity extends AppCompatActivity {

    TextView textView_main, textView_sub;

    String vocab_file_name;
    int[] word_indices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Process the given intent
        Intent intent = getIntent();
        vocab_file_name = intent.getStringExtra("vocab_file_name");
        word_indices = intent.getIntArrayExtra("indices");

        // Layout
        setContentView(R.layout.activity_training);

        Toolbar toolbar = findViewById(R.id.toolbar_training);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) { throw new NullPointerException(); }
        else { actionBar.setDisplayHomeAsUpEnabled(true); }

        // TextViews
        textView_main = findViewById(R.id.text_training_main);
        textView_sub = findViewById(R.id.text_training_sub);

        // For test purpose
        textView_main.setText(vocab_file_name);
    }
}
