package com.example.android.to_do_list_2_0.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.to_do_list_2_0.R;

public class addToDo extends Fragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_to_do_layout, container, false);

        EditText editText = view.findViewById(R.id.add_todo_edit_text);
        editText.requestFocus();
        editText.setOnEditorActionListener(new DoneOnEditorActionListener());
        return view;
    }

    private class DoneOnEditorActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            return false;
        }
    }
}
