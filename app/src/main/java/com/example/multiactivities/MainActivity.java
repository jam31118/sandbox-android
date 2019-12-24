package com.example.multiactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView_vocab_names;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ToolBar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        // ListView set-up
        listView_vocab_names = findViewById(R.id.listView_vocab_names);
        ArrayList<String> vocabNameList = new ArrayList<String>(
                Arrays.asList("N5","N4","N3","N2","N1"));
        VocabListAdapter vocabListAdapter = new VocabListAdapter(vocabNameList);
        listView_vocab_names.setAdapter(vocabListAdapter);

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
                intent.putExtra("level",5);
                intent.putExtra("vocab_name", vocab_name);
                startActivity(intent);
            }
        });

//        // TextView
//        textView = findViewById(R.id.text_N5);
//        textView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Send an intent to open up the second Activity
//                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
//                intent.putExtra("level",5);
//                startActivity(intent);
//            }
//        });
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
            return textView_vocab_name;
        }
    }
}
