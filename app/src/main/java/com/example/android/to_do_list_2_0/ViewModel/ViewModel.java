package com.example.android.to_do_list_2_0.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.to_do_list_2_0.MainActivity;
import com.example.android.to_do_list_2_0.Room.Repository;
import com.example.android.to_do_list_2_0.Room.Task;
import java.util.List;

//Holds the UI data
public class ViewModel extends AndroidViewModel {

    private LiveData<List<Task>> allTasks;
    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        allTasks = repository.getAllTasks();
    }

    public void insert(String todo, int id) {

        Task task = new Task();
        task.setUserTask(todo);
        task.setId(id);
        repository.insert(task);
    }

    public List<Task> readAll()
    {
        return repository.readAll();
    }

    public Task readTask(int id)
    {
        return repository.read(id);
    }

}
