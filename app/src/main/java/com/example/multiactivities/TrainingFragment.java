package com.example.multiactivities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TrainingFragment extends Fragment {

    private static final String DEBUG_TAG = "TrainingFragment";

    private boolean atFrontSide, word_sound_hidden;
    private String word_target = null, word_sound = null, word_meaning = null;

    private TextView textView_main, textView_sub;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_training, container, false);

        // TextViews
        textView_main = view.findViewById(R.id.text_training_main);
        textView_sub = view.findViewById(R.id.text_training_sub);


        Log.d(DEBUG_TAG, "after the initialization of TextViews");

        Bundle args = getArguments();
        if (args == null) {
            Log.d(DEBUG_TAG, "'args' is null");
            return view;
        }
        List<String> word = args.getStringArrayList("word");

        if (word == null) {
            Log.d(DEBUG_TAG, "'word' is null");
            return view;
        }


        // Determine the side
        atFrontSide = true;
        word_sound_hidden = true;

        // Extract word
        word_target = word.get(0);
        word_sound = word.get(1);
        word_meaning = word.get(2);

        Log.d(DEBUG_TAG, "'word_target': " + word_target);

        if (initializeTextInTextViews() != 0) {
            Log.e(DEBUG_TAG, "Failed to initialize the text in TextViews");
            return view;
        }
//        textView_main.setText(word_target);
//        textView_sub.setText(""); // the pronunciation is hidden at first

        // When each TextView is clicked
        textView_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (atFrontSide) {
                    textView_main.setText(word_meaning);
                    textView_sub.setText("");
                } else {
                    textView_main.setText(word_target);
                    if (word_sound_hidden) {
                        textView_sub.setText("");
                    } else {
                        textView_sub.setText(word_sound);
                    }
                }
                atFrontSide = !atFrontSide;
            }
        });

        textView_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (atFrontSide) {
                    if (word_sound_hidden) {
                        textView_sub.setText(word_sound);
                    } else {
                        textView_sub.setText("");
                    }
                    word_sound_hidden = !word_sound_hidden;
                }
            }
        });

//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                textView_main.setText(word_meaning);
//                textView_sub.setText("");
//            }
//        });

        return view;
    }

    private boolean nullWordExists() {
        return (word_target == null) || (word_sound == null) || (word_meaning == null);
    }

    private int initializeTextInTextViews() {
        if (nullWordExists()) { return 1; }
        if (atFrontSide) {
            textView_main.setText(word_target);
            if (word_sound_hidden) {
                textView_sub.setText("");
            } else {
                textView_sub.setText(word_sound);
            }
        } else {
            textView_main.setText(word_meaning);
            textView_main.setText("");
        }
        return 0;
    }

//    private int setTextAsFlipped() {
//        if (! allWordsAreNotNull()) { return 1; }
//        atFrontSide = !atFrontSide;
//        if (atFrontSide) {
//            textView_main.setText(word_target);
//        } else {
//            textView_main.setText(word_meaning);
//            textView_main.setText("");
//        }
//        return 0;
//    }
}