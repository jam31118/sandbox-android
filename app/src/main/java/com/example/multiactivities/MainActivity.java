package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME_VOCAB_LIST = "vocab.list";
    private static final String MESG_FORM_MISSING_FILE = "Failed to open %1$s file";
    private static final String TAG = "MainActivity";
    ListView listView_vocab_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //// Load required information
        // Load the list of vocabularies
        ArrayList<String> vocabNameList = loadVocabList();


        //// Construct Layout
        // Set the parent layout
        setContentView(R.layout.activity_main);

        // ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // ListView set-up
        listView_vocab_names = findViewById(R.id.listView_vocab_names);
        listView_vocab_names.setAdapter(new VocabListAdapter(vocabNameList));


        //// Set how each component should respond when touched
        // When each vocab name is clicked:
        listView_vocab_names.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Logging
                String vocab_name = listView_vocab_names.getAdapter().getItem(i).toString();
                Toast.makeText(getApplicationContext(),
                        "Clicked vocab name: " + vocab_name, Toast.LENGTH_SHORT).show();

                // Send an intent to open up the second Activity
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
//                intent.putExtra("level",5);
                intent.putExtra("vocab_name", vocab_name);
                startActivity(intent);
            }
        });
    }

    private ArrayList<String> loadVocabList() {

        // Load the list of vocabularies
        ArrayList<String> vocabNameList = new ArrayList<>();
        AssetManager assetManager = getResources().getAssets();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(assetManager.open(FILENAME_VOCAB_LIST))))
        {
            List<String> asset_file_list = Arrays.asList(assetManager.list(""));

            for (String line, trimmed_line, vocab_file_name; (line = br.readLine()) != null; ) {
                trimmed_line = line.trim();
                if (trimmed_line.length() < 1) { continue; }
                vocab_file_name = String.format(getString(R.string.vocab_file_name_format), trimmed_line);

                if (asset_file_list.contains(vocab_file_name)) { vocabNameList.add(trimmed_line); }
                else {
                    String err_mesg = String.format(MESG_FORM_MISSING_FILE, vocab_file_name);
                    Log.e(TAG, err_mesg);
                    Toast.makeText(getApplicationContext(), err_mesg, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
        catch (IOException e) {
            Log.e(TAG, String.format(MESG_FORM_MISSING_FILE, FILENAME_VOCAB_LIST), e);
        }

        // Check whether the fetched number of vocabularies are not zero or below
        if (vocabNameList.size() < 1) {
            Log.e(TAG, "Unexpected number of vocab names got");
            throw new RuntimeException();
        }

        return vocabNameList;
    }


    class VocabListAdapter extends BaseAdapter {

        List<String> vocab_name_list;

        private VocabListAdapter(ArrayList<String> vocab_name_list) {
            this.vocab_name_list = vocab_name_list;
        }

        @Override
        public int getCount() {
            return vocab_name_list.size();
        }

        @Override
        public Object getItem(int i) {
            return vocab_name_list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView_vocab_name = new TextView(getApplicationContext());
            textView_vocab_name.setText(getItem(i).toString());
            textView_vocab_name.setTextSize(50);
            textView_vocab_name.setGravity(Gravity.CENTER);
            return textView_vocab_name;
        }
    }
}
