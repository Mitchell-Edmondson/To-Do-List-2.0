package com.example.android.to_do_list_2_0.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.arch.lifecycle.LiveData;

import java.util.List;

@Dao
public interface taskDao {

    @Insert(onConflict = 5)
    void insertUserTask(Task task);

    @Query("SELECT * FROM userTask")
    List<Task> getAllTasks();

    @Query("DELETE FROM userTask")
    void deleteAllTasks();

    @Delete
    int deleteTask(Task task);

    @Update
    void updateUser(Task task);
}
