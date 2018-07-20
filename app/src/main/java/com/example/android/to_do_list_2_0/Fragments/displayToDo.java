package com.example.android.to_do_list_2_0.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.to_do_list_2_0.MainActivity;
import com.example.android.to_do_list_2_0.R;
import com.example.android.to_do_list_2_0.Room.Task;

public class displayToDo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Get the position of the button that was clicked
        Bundle bundle = getArguments();
        Task task = (Task) bundle.getSerializable("todoTask");

        View view = inflater.inflate(R.layout.display_to_do_layout, container, false);

        //Set the textview to display the ToDo
        TextView textView = view.findViewById(R.id.textview_display_to_do);
        textView.setText(String.valueOf(task.getUserTask()));

        //Set the id of the button equal to the id of the task so we can access it
        Button button = view.findViewById(R.id.button_delete_to_do);
        button.setId(task.getId());

        button = view.findViewById(R.id.button_update_to_do);
        button.setId(task.getId());

        return view;
    }
}
