package com.example.android.to_do_list_2_0.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;

@Entity(tableName = "userTask")
public class Task {

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
