package com.example.android.to_do_list_2_0;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.to_do_list_2_0.Room.Task;
import com.example.android.to_do_list_2_0.Room.taskDao;
import com.example.android.to_do_list_2_0.Room.taskDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class database_Test {

/*
    @Test
    public void testPopUpWindow_AddTimeButton(){

        //Click add time button
        onView(withId(R.id.button_test_time)).perform(click());

        //Check if the button next to the task is updated with the correct time
        onView((withId(R.id.time_picker))).perform(PickerActions.setTime(12, 11));
        onView(withId(R.id.button_add_time)).check(matches(withText("12:11PM")));
    }
*/

    private taskDao mUserDao;
    private taskDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, taskDatabase.class).build();
        mUserDao = mDb.taskDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void testInsertandRead(){

        Task user = new Task();
        user.setTime("11:32PM");
        user.setId(0);
        user.setUserTask("First Task");
        mUserDao.insertUserTask(user);
        Task byName =  mUserDao.getTask(0);

        //Check all 3 attributes of task object to make sure we have the same
        assertEquals(byName.getTime(), user.getTime());
        assertEquals(byName.getUserTask(), user.getUserTask());
        assertEquals(byName.getId(), user.getId());
    }
}
