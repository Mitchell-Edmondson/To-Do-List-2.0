package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.to_do_list_2_0.Adapters.myAdapter;
import com.example.android.to_do_list_2_0.Fragments.addToDo;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.to_do_list_2_0.Room.Task;
import com.example.android.to_do_list_2_0.Room.taskDatabase;
import com.example.android.to_do_list_2_0.ViewModel.ViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

// TODO: 16/07/18
/*
    Display Tasks in a recyclerView

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

        //Set up recycler view
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
        //Exit the fragment
        getSupportFragmentManager().popBackStack();
        //Get the text the user entered
        EditText editText = findViewById(R.id.add_todo_edit_text);
        //Get the id of the task
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //Insert task into database
        viewModel.insert(editText.getText().toString(), mRecyclerView.getChildCount() - 1);
        //Read the task back
        Task task = viewModel.readTask(mRecyclerView.getChildCount() - 1);

    }
}
