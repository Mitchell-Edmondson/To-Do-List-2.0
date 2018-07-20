package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.to_do_list_2_0.Fragments.displayToDo;
import com.example.android.to_do_list_2_0.Fragments.updateToDo;
import com.example.android.to_do_list_2_0.Room.Task;
import com.example.android.to_do_list_2_0.Room.taskDatabase;
import com.example.android.to_do_list_2_0.ViewModel.ViewModel;
import java.io.Serializable;
import java.util.ArrayList;

// TODO: 16/07/18
/*
    Work on start up

    --Debug--
    Can press + button multiple times

*/
public class MainActivity extends AppCompatActivity {

    //Variable for viewmodel
    private ViewModel viewModel;
    //Variable for database
    public static taskDatabase myTaskDatabase;

    //Variables for recyclerview setup
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Task> todoTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creating the ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        //Initalize the database
        myTaskDatabase = Room.databaseBuilder(getApplicationContext(), taskDatabase.class,
                "userTaskDB").build();

        //Check the database for any pre-existing tasks
        todoTask = viewModel.startUp();

        //Set up recycler view
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new myAdapter(todoTask, this);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    //User hit "+" button. Start the fragment to enter a todoTask
    public void createAddToDo(View view) {

        //Start fragment for adding a ToDo
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addToDo fragment = new addToDo();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);

        //Force the keyboard to pop up
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        fragmentTransaction.commit();
    }

    //User hit the "Done" button. Add in the new Task to the database and display task on screen
    public void updateScreen(View view){

        //Get the text the user entered
        EditText editText = findViewById(R.id.add_todo_edit_text);
        //Exit the fragment
        getSupportFragmentManager().popBackStack();
        //Get the id of the task
        //Insert task into database and add it to arraylist
        Log.d("insertTask", "child count (ID) = " + String.valueOf(mLayoutManager.getChildCount()));
        Long ID = viewModel.insert(editText.getText().toString(), mLayoutManager.getItemCount());
        Task task = new Task();
        task.setUserTask(editText.getText().toString());
        Log.d("insertTask", "Actual ID of task = " + String.valueOf(ID));
        task.setId(ID.intValue());
        todoTask.add(task);
        //Notify recyclerview and add it to screen
        mAdapter.notifyItemInserted(todoTask.size() - 1);
        mRecyclerView.scrollToPosition(todoTask.size() - 1);
    }

    //User clicked on a todoTask in recyclerview. Start the fragment that displays the task
    public void displayToDo(int ID){

        //Check to get rid of the previous displayed fragment
        Fragment test = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(test != null && test.isVisible()){
            //Exit the fragment
            getSupportFragmentManager().popBackStack();
        }

        //Read the task with the given ID
        Task task = viewModel.readTask(ID);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Pass in the task to the fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("todoTask", (Serializable) task);

        displayToDo fragment = new displayToDo();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    //User hit the "Delete ToDo Button"
    public void deleteToDo(View view){

        //Exit the fragment
        getSupportFragmentManager().popBackStack();

        //Delete todoTask from database (ID of button is equal to ID in database)
        viewModel.deleteTask(view.getId());
        //Remove todoTask from list
        Log.d("deleteTask", "view id = " + String.valueOf(view.getId()));
        todoTask = viewModel.removeTask(view.getId(), todoTask);
        //Update screen (getting rid of button to display this task)
        mAdapter.notifyItemRemoved(view.getId());
        mAdapter.notifyItemRangeChanged(0, todoTask.size());
    }

    //User hits the "Update ToDo Button." Start the fragment
    public void updateToDo(View view){

        //Send the textview string to the fragment in a bundle
        TextView textView = findViewById(R.id.textview_display_to_do);
        Bundle bundle = new Bundle();
        bundle.putString("todoTask", textView.getText().toString());

        //Start fragment for updating a ToDo
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        updateToDo fragment = new updateToDo();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);

        //Force the keyboard to pop up
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        fragmentTransaction.commit();
    }

    //User hits the "Update Button" so now we actual update the db and screen
    public void updateToDoTask(View view){

    }
}
