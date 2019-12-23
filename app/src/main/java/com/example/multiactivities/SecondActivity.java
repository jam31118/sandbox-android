package com.example.multiactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    SetAdapter setAdapter;

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
        int level = intent_from_level_selection.getIntExtra("level",0);

        Toast.makeText(getApplicationContext(),
                "Selected level: N" + level, Toast.LENGTH_SHORT).show();

        listView = findViewById(R.id.listView_word_set);

        setAdapter = new SetAdapter(level);
        listView.setAdapter(setAdapter);

//        if (level_name == "N5") {
//
//        }

    }

//    class SingleWordSetView extends LinearLayout {
//
//        TextView text_view_word_set;
//
//        public SingleWordSetView(Context context) {
//            super(context);
//            init(context);
//        }
//
//        public SingleWordSetView(Context context, AttributeSet attributeSet) {
//            super(context, attributeSet);
//            init(context);
//        }
//
//        public void init(Context context) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
//                    Context.LAYOUT_INFLATER_SERVICE);
//            inflater.inflate(R.layout.textview_per_wordset,this,true);
//
//            text_view_word_set = findViewById(R.id.text_view_word_set);
//        }
//
//        public void setText(String text) { text_view_word_set.setText(text); }
//    }

    class SetAdapter extends BaseAdapter {

        int level;
        int count;

        public SetAdapter(int level) {
            this.level = level;
            this.count = this.level * 2;
        }

        @Override
        public int getCount() {
            return this.count;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public Object getItem(int i) {
            return i * 2;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView_word_set = new TextView(getApplicationContext());
//            SingleWordSetView single_word_set_view = new SingleWordSetView(getApplicationContext());
            textView_word_set.setText("Word Set " + i);
            return textView_word_set;
        }
    }
}
