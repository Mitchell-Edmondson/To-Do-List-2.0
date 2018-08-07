package com.example.android.to_do_list_2_0.Activities;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.to_do_list_2_0.BuildConfig;
import com.example.android.to_do_list_2_0.Room.taskDao;
import com.example.android.to_do_list_2_0.Room.taskDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    private taskDao mTaskDao;
    private taskDatabase mDatabase;


    @Test
    public void shouldNotBeNull(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(mainActivity);
    }

}