package com.example.android.to_do_list_2_0.Room;

import android.os.AsyncTask;
import android.util.Log;
import android.arch.lifecycle.LiveData;
import java.util.List;
import static com.example.android.to_do_list_2_0.MainActivity.myTaskDatabase;

//Handles data operations to database
public class Repository {
    private Task task;
    private LiveData<List<Task>> allTasks;

    public static class insertTask extends AsyncTask<Task, Void, Void>
    {

        @Override
        protected Void doInBackground(Task... params) {
            Log.d("insertTask", "inserting " + String.valueOf(params[0].getUserTask()));
            myTaskDatabase.taskDao().insertUserTask(params[0]);
            return null;
        }
    }

    public static class readTask extends AsyncTask<Void, Void, List<Task>>
    {
        List<Task> temp;

        @Override
        protected List<Task> doInBackground(Void... voids) {
            temp = (List<Task>) myTaskDatabase.taskDao().getAllTasks();
            Log.d("Checking Tasks", "temp list = " + String.valueOf(temp.size()));
            if(temp.size() == 0)
            {
                return null;
            }
            return temp;
        }
    }

    public static class deleteAllTasks extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            myTaskDatabase.taskDao().deleteAllTasks();
            return null;
        }
    }

    public static class deleteTask extends AsyncTask<Task, Void, Integer>
    {
        @Override
        protected Integer doInBackground(Task... params) {
            Log.d("deleteTask", "task = " +  params[0].getUserTask());
            Log.d("deleteTask", "task id = " +  params[0].getId());
            return myTaskDatabase.taskDao().deleteTask(params[0]);
        }
    }

    public static class updateTask extends AsyncTask<Task, Void, Void>
    {

        @Override
        protected Void doInBackground(Task... tasks) {
            Log.d("updateTask", "Updating " + tasks[0].getUserTask());
            myTaskDatabase.taskDao().updateUser(tasks[0]);
            return null;
        }
    }

}
