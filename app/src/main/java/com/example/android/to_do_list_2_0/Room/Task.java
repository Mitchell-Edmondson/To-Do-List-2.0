package com.example.android.to_do_list_2_0.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;

import java.io.Serializable;

@Entity(tableName = "userTask")
//Implements serializable to be able to pass task object to fragment
public class Task implements Serializable {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "userTask")
    private String userTask;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setUserTask(String task){
        this.userTask = task;
    }
    public String getUserTask() {
        return this.userTask;
    }
}