package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.to_do_list_2_0.Room.Task;
import com.example.android.to_do_list_2_0.Room.taskDatabase;
import com.example.android.to_do_list_2_0.ViewModel.ViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewModel viewModel;
    public static taskDatabase myTaskDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creating the ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        //Initalize the database
        myTaskDatabase = Room.databaseBuilder(getApplicationContext(), taskDatabase.class,
                "userTaskDB").build();
    }

    public void putindb(View view)
    {
        EditText editText = findViewById(R.id.edit_text);
        Task task = new Task();
        task.setId(0);
        task.setUserTask(editText.getText().toString());
        viewModel.insert(task);

        List<Task> list = viewModel.readAll();
        TextView textView = findViewById(R.id.text_view);
        textView.setText(list.get(0).getUserTask());
    }

    public void updateText(List<Task> list){
        //Update screen
        TextView textView = findViewById(R.id.text_view);
        textView.setText(list.get(0).getUserTask());
    }
}
