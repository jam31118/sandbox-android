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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_training, container, false);

        // TextViews
        TextView textView_main = view.findViewById(R.id.text_training_main);
        TextView textView_sub = view.findViewById(R.id.text_training_sub);


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


//        String vocab_file_name = args.getString("vocab_file_name");
//        ArrayList<List<String>> arrayList_vocab = getArrayListOfWordsFromVocabFile(
//                vocab_file_name);

        // For test purpose
//        List<String> word;
//        word = arrayList_vocab.get(word_indices[0]);
        String word_target;
//        String word_sound, word_meaning;
        word_target = word.get(0);
//        word_sound = word.get(1);
//        word_meaning = word.get(2);

        Log.d(DEBUG_TAG, "'word_target': " + word_target);

        textView_main.setText(word_target);
        textView_sub.setText(""); // the pronunciation is hidden at first

        // When each TextView is clicked

        return view;
    }
}