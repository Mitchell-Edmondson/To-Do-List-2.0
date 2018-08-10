package com.example.android.to_do_list_2_0;


import android.os.AsyncTask;

import com.example.android.to_do_list_2_0.myActivities.MainActivity;
import com.example.android.to_do_list_2_0.myActivities.addToDo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class activity_test {

    @Test
    public void addToDoShouldNotBeNull(){
        addToDo myAddToDo = Robolectric.setupActivity(addToDo.class);
        assertNotNull(myAddToDo);
    }

    @Test
    public void mainActivityShouldNotBeNull(){
        setUpMainActivity mainActivity = new setUpMainActivity();
        assertNotNull(mainActivity);
    }

    private static class setUpMainActivity extends AsyncTask<Void, Void, MainActivity>{

        @Override
        protected MainActivity doInBackground(Void... voids) {
            MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
            return mainActivity;
        }
    }
}
