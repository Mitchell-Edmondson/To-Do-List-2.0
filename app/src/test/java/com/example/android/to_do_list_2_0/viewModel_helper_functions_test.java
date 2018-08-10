package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;

import com.example.android.to_do_list_2_0.myActivities.MainActivity;
import com.example.android.to_do_list_2_0.myActivities.addToDo;
import com.example.android.to_do_list_2_0.viewModel.ViewModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class viewModel_helper_functions_test {

    @Mock
    private ViewModel viewModel;

    @Test
    public void testViewModel(){
        String time = "1:22PM";
        String hour = viewModel.getHour("1:22",  time.indexOf(':'));
        assertEquals("1", hour);
    }
}
