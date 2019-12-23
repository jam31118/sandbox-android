package com.example.multiactivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new NullPointerException();
        } else {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // When the home button (or 'up' button) is pressed,
    // this activity is closed and go back to upper activity.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        Toast.makeText(getApplicationContext(),
//                "item.getItemId()=="+item.getItemId()+"\n"+"R.id.home=="+R.id.home,
//                Toast.LENGTH_SHORT).show();

        // [NOTE] This may be fixed after the role of `R.id.home` become apparent.
//        if (item.getItemId() == R.id.home) { finish(); }
        finish();

        return super.onOptionsItemSelected(item);
    }

}
