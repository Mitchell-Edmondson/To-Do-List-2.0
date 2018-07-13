package com.example.android.to_do_list_2_0.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class taskDatabase  extends RoomDatabase {
    private taskDao taskDao;

    public abstract taskDao taskDao();

    private static taskDatabase INSTANCE;

    public static taskDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (taskDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            taskDatabase.class, "tasks_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
