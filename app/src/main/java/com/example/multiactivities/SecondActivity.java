package com.example.multiactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    ListView listView;
    SetAdapter setAdapter;

    static final int numOfWordsPerSet = 25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_second);
        setSupportActionBar(toolbar);

        // When the home button (or 'up' button) is pressed,
        // this activity is closed and go back to it parent activity defined in the manifest file
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) { throw new NullPointerException(); }
        else { actionBar.setDisplayHomeAsUpEnabled(true); }

        // Process given intent from the parent activity
        Intent intent_from_level_selection = getIntent();
//        int level = intent_from_level_selection.getIntExtra("level",0);
        String vocab_name, vocab_file_name;
        int numOfWords;
        vocab_name = intent_from_level_selection.getStringExtra("vocab_name");
        vocab_file_name = String.format(getString(R.string.vocab_file_name_format), vocab_name);
        numOfWords = getNumberOfWordsFromVocabFile(vocab_file_name);

        Toast.makeText(getApplicationContext(),
                "Selected vocab name: " + vocab_name, Toast.LENGTH_SHORT).show();

        listView = findViewById(R.id.listView_word_set);
        setAdapter = new SetAdapter(numOfWords, numOfWordsPerSet);
        listView.setAdapter(setAdapter);

    }

    public int getNumberOfWordsFromVocabFile(String vocab_file_name) {

        int numOfWords = 0;

        AssetManager assetManager = getResources().getAssets();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(assetManager.open(vocab_file_name))))
        { for ( ; br.readLine() != null; ) { numOfWords++; } }
        catch (IOException e) {
            String err_message = String.format(getString(R.string.file_open_err_message), vocab_file_name);
            Log.e(TAG, err_message, e);
            finish();
        }

        return numOfWords;
    }

    public ArrayList<List<String>> getArrayListOfWordsFromVocabFile(String vocab_file_name) {

        ArrayList<List<String>> arrayListVocab = new ArrayList<>();

        AssetManager assetManager = getResources().getAssets();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(assetManager.open(vocab_file_name)))) {
            for (String line; (line = br.readLine()) != null; ) {
                arrayListVocab.add(new ArrayList<>(Arrays.asList(line.split("\\s*,\\s*"))));
            }
        } catch (IOException e) {
            String err_message = String.format(getString(R.string.file_open_err_message), vocab_file_name);
            Log.e(TAG, err_message, e);
            finish();
        }

        return arrayListVocab;
    }


    class SingleWordSetView extends LinearLayout {

        TextView text_view_word_set;

        public SingleWordSetView(Context context) {
            super(context);
            init(context);
        }

        public SingleWordSetView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            init(context);
        }

        public void init(Context context) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.textview_per_wordset,this,true);

            text_view_word_set = findViewById(R.id.text_view_word_set);
        }

        public void setText_word_set(String text) {
            text_view_word_set.setText(text);
        }

    }

    class SetAdapter extends BaseAdapter {

        int numOfWords, numOfWordsPerSet, numOfSets;

        private SetAdapter(int numOfWords, int numOfWordsPerSet) {
            this.numOfWords = numOfWords;
            this.numOfWordsPerSet = numOfWordsPerSet;
            this.numOfSets = (numOfWords + numOfWordsPerSet - 1) / numOfWordsPerSet;
        }

        @Override
        public int getCount() {
            return numOfSets;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            SingleWordSetView single_word_set_view = new SingleWordSetView(getApplicationContext());
            int numOfDigits = -1;
            if (numOfWords > 0) { numOfDigits = (int) Math.log10(numOfWords) + 1; }
            else if (numOfWords == 0) { numOfDigits = 1; }
            else { Log.e(TAG, "Invalid value for number of words given"); finish(); }
            String word_set_label_form = "WORD SET %0"+numOfDigits+"d";
            String word_set_label = String.format(Locale.getDefault(), word_set_label_form, i);
            single_word_set_view.setText_word_set(word_set_label);
            return single_word_set_view;
        }
    }
}
