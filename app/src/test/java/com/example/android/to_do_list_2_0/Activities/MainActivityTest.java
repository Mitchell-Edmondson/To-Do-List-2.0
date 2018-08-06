package com.example.android.to_do_list_2_0.Activities;

import com.example.android.to_do_list_2_0.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {

    @Test
    public void shouldNotBeNull(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(mainActivity);
    }

}