package com.example.android.to_do_list_2_0.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.to_do_list_2_0.Room.Repository;
import com.example.android.to_do_list_2_0.Room.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//Holds the UI data
public class ViewModel extends AndroidViewModel {

    private LiveData<List<Task>> allTasks;
    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allTasks = repository.getAllTasks();
    }

    public Long insert(String todo, int id, String time) {

        Task task = new Task();
        task.setUserTask(todo);
        task.setId(id);
        task.setTime(time);
        return repository.insert(task);
    }

    public List<Task> readAll()
    {
        return repository.readAll();
    }

    public Task readTask(int id)
    {
        return repository.read(id);
    }

    //Checks the database for any existing tasks when first being opened
    public ArrayList<Task> startUp() {
        List<Task> tasks = repository.readAll();
        //There are pre-existing tasks in database
        if(tasks != null){

            ArrayList<Task> todoTasks = new ArrayList<Task>();
            //Add each user task to arraylist
            for(Task t: tasks){
                todoTasks.add(t);
                Log.d("startUp", "Have task = " + t.getUserTask() + " and ID = " + String.valueOf(t.getId()));
            }
            return todoTasks;
        }
        return new ArrayList<Task>();

    }

    public void deleteTask(Integer ID){
        repository.delete(ID);
    }

    //Removes the task from the arraylist. Have to search through the arraylist for the right index,
    //because the tasks can move around each time the app is opened
    public ArrayList<Task> removeTask(int ID, ArrayList<Task> todoTask){

        for(Task t: todoTask) {
            if (ID == t.getId()) {
                Log.d("deleteTask", "task removing = " + t.getUserTask());
                todoTask.remove(t);
                return todoTask;
            }
        }
        return null;
    }

    public void updateTask(Task task){
        repository.update(task);
    }

    public String getHour(String time, int index){
        String hour = "";
        for(int i = 0; i < index; i++){
            hour += time.charAt(i);
        }
        return hour;
    }

    public String getMinute(String time, int index){
        String minute = "";
        //Plus 1 to get past the ':', -2 to stop before AM or PM
        for(int i = index + 1; i < time.length() - 2; i++){
            minute += time.charAt(i);
        }
        return minute;
    }

    public String getAmOrPm(String time, int index){
        String amOrPm = "";
        //+3 to get to the start of am or pm
        for(int i = index + 3; i < time.length(); i++){
            amOrPm += time.charAt(i);
        }
        return amOrPm;
    }

    public Calendar setCalandar(String hour, String minute){
        Calendar calendar = Calendar.getInstance();
        Log.d("alarm", "hour = " + String.valueOf(hour));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
        Log.d("alarm", "minute = " + String.valueOf(minute));
        calendar.set(Calendar.MINUTE, Integer.valueOf(minute));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
