package com.example.android.to_do_list_2_0.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.to_do_list_2_0.R;

public class displayToDo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Get the position of the button that was clicked
        Bundle bundle = getArguments();
        View view = inflater.inflate(R.layout.display_to_do_layout, container, false);
        TextView textView = view.findViewById(R.id.textview_display_to_do);
        textView.setText(String.valueOf(bundle.get("Index")));
        return view;
    }
}
