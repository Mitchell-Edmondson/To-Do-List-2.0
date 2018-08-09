package com.example.android.to_do_list_2_0.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.arch.lifecycle.LiveData;

import java.util.List;

@Dao
public interface taskDao {

    @Insert(onConflict = 5)
    Long insertUserTask(Task task);

    @Query("SELECT * FROM userTask")
    LiveData<List<Task>> getAllTasksLD();

    @Query("SELECT * FROM userTask")
    List<Task> getAllTasks();

    @Query("SELECT * FROM userTask WHERE ID = :id")
    Task getTask(Integer id);

    @Query("DELETE FROM userTask")
    void deleteAllTasks();

    @Query("DELETE FROM userTask WHERE ID = :ID" )
    int deleteTask(Integer ID);

    @Update
    void updateUser(Task task);
}
