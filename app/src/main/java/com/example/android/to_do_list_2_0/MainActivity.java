package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.example.android.to_do_list_2_0.Adapters.myAdapter;
import com.example.android.to_do_list_2_0.Fragments.addToDo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.example.android.to_do_list_2_0.Room.Task;
import com.example.android.to_do_list_2_0.Room.taskDatabase;
import com.example.android.to_do_list_2_0.ViewModel.ViewModel;

import java.util.ArrayList;
import java.util.List;

// TODO: 16/07/18
/*
    Work on start up

*/
public class MainActivity extends AppCompatActivity {

    private ViewModel viewModel;
    public static taskDatabase myTaskDatabase;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> todoTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creating the ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        //Initalize the database
        myTaskDatabase = Room.databaseBuilder(getApplicationContext(), taskDatabase.class,
                "userTaskDB").build();

        todoTask = viewModel.startUp();

        //Check if there are not pre-existing tasks
        if(todoTask == null){
            todoTask = new ArrayList<String>();
            todoTask.add(" ");
        }

        //Set up recycler view
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new myAdapter(todoTask);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    //Start the fragment to create a To Do item
    public void createAddToDo(View view)
    {
        //Start fragment for adding a To Do
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addToDo fragment = new addToDo();
        fragmentTransaction.replace(R.id.add_todo_container, fragment);
        fragmentTransaction.addToBackStack(null);

        //Force the keyboard to pop up
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        fragmentTransaction.commit();

    }

    public void updateScreen(View view){

        //Get the text the user entered
        EditText editText = findViewById(R.id.add_todo_edit_text);
        //Exit the fragment
        getSupportFragmentManager().popBackStack();
        //Get the id of the task
        //Insert task into database and add it to arraylist
        viewModel.insert(editText.getText().toString(), mRecyclerView.getChildCount() - 1);
        todoTask.add(editText.getText().toString());
        //Notify recyclerview and add it to screen
        mAdapter.notifyItemInserted(todoTask.size() - 1);
        mRecyclerView.scrollToPosition(todoTask.size() - 1);
    }
}
