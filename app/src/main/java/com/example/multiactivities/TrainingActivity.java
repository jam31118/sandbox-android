package com.example.multiactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingActivity extends AppCompatActivity {

    private static final String TAG = "TrainingActivity";

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

        //// Set Layout
        setContentView(R.layout.activity_training);

        // Set Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_training);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) { throw new NullPointerException(); }
        else { actionBar.setDisplayHomeAsUpEnabled(true); }

        // Set ViewPager2
        ViewPager2 viewPager2 = findViewById(R.id.pager);
        WordTrainingPagerAdapter pagerAdapter = new WordTrainingPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

    }

    private class WordTrainingPagerAdapter extends FragmentStateAdapter {

        private WordTrainingPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            TrainingFragment trainingFragment = new TrainingFragment();

            arrayList_vocab = getArrayListOfWordsFromVocabFile(vocab_file_name);
            ArrayList<String> word = new ArrayList<>(arrayList_vocab.get(word_indices[position]));
            Bundle args = new Bundle();
            args.putStringArrayList("word", word);
            trainingFragment.setArguments(args);

            return trainingFragment;
        }

        @Override
        public int getItemCount() {
            return word_indices.length;
        }
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
