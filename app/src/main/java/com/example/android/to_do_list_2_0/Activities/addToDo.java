package com.example.android.to_do_list_2_0.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.to_do_list_2_0.Adapters.myAdapter;
import com.example.android.to_do_list_2_0.R;
import com.example.android.to_do_list_2_0.Room.Task;
import com.example.android.to_do_list_2_0.ViewModel.ViewModel;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static com.example.android.to_do_list_2_0.Activities.MainActivity.hideKeyboard;

public class addToDo extends AppCompatActivity {


    private ViewModel viewModel;
    private ArrayList<Task> todoTask = new ArrayList<Task>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
    }
    //User hit the "Done" button. Add in the new Task to the database and display task on screen
    public void addToDo(View view){

        //Get the text the user entered
        EditText editText = findViewById(R.id.edit_text_add_todo);

        Intent replyIntent = new Intent();
        replyIntent.putExtra("todoTask", editText.getText().toString());

        //Get rid of the onscreen keyboard
        hideKeyboard(this);
        //Create toast saying todoTask added
        Toast.makeText(this, "ToDo Created!", Toast.LENGTH_SHORT).show();

        setResult(RESULT_OK, replyIntent);
        finish();

    }
}
