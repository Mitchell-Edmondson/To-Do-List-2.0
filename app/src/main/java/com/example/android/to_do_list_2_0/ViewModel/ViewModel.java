package com.example.android.to_do_list_2_0.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.to_do_list_2_0.Room.Task;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private LiveData<List<Task>> allTasks;

    public ViewModel(@NonNull Application application) {
        super(application);
    }
}
