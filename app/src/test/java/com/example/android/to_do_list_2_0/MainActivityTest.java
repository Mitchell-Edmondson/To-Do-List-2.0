package com.example.android.to_do_list_2_0;

import com.example.android.to_do_list_2_0.Activities.addToDo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    private addToDo m;


    @Test
    public void shouldNotBeNull(){
        //addToDo mainActivity = Robolectric.setupActivity(addToDo.class);
        //assertNotNull(mainActivity);
    }

}