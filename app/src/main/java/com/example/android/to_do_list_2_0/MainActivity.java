package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.android.to_do_list_2_0.Fragments.addToDo;

import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.android.to_do_list_2_0.Room.Task;
import com.example.android.to_do_list_2_0.Room.taskDatabase;
import com.example.android.to_do_list_2_0.ViewModel.ViewModel;

import java.util.List;


// TODO: 16/07/18
/*


*/
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

    //Start the fragment to create a To Do item
    public void createAddToDo(View view)
    {
        //Clear screen
        ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout);
        //Removes the "+" button
        constraintLayout.removeViewAt(1);

        //Start fragment for adding a To Do
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        addToDo fragment = new addToDo();
        fragmentTransaction.replace(R.id.add_todo_container, fragment);

        //Force the keyboard to pop up
        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        
        fragmentTransaction.commit();

    }

    public void updateText(){
        //Update screen
        ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout);
        TextView textView = new TextView(this);
        constraintLayout.addView(textView);
        List<Task> list = viewModel.readAll();
        textView.setText(list.get(constraintLayout.getChildCount() - 3).getUserTask());
    }
}
