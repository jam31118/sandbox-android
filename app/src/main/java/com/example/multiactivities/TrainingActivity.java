package com.example.multiactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingActivity extends AppCompatActivity {

    private static final String TAG = "TrainingActivitiy";

    TextView textView_main, textView_sub;

    String vocab_file_name;
    int[] word_indices;
    ArrayList<List<String>> arrayList_vocab;

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


        arrayList_vocab = getArrayListOfWordsFromVocabFile(vocab_file_name);


        // For test purpose
        List<String> word;
        word = arrayList_vocab.get(word_indices[0]);
        String word_target, word_sound, word_meaning;
        word_target = word.get(0);
        word_sound = word.get(1);
        word_meaning = word.get(2);

        textView_main.setText(word_target);
        textView_sub.setText(word_sound);
    }


    public ArrayList<List<String>> getArrayListOfWordsFromVocabFile(String vocab_file_name) {

        ArrayList<List<String>> arrayListVocab = new ArrayList<>();

        AssetManager assetManager = getResources().getAssets();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(assetManager.open(vocab_file_name)))) {
            for (String line; (line = br.readLine()) != null; ) {
                arrayListVocab.add(new ArrayList<>(Arrays.asList(line.split("\\s*\\|\\s*"))));
            }
        } catch (IOException e) {
            String err_message = String.format(
                    getString(R.string.file_open_err_message), vocab_file_name);
            Log.e(TAG, err_message, e);
            finish();
        }

        return arrayListVocab;
    }

}
