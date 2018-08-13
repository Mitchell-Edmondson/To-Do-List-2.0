package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.to_do_list_2_0.myActivities.MainActivity;
import com.example.android.to_do_list_2_0.myActivities.addToDo;
import com.example.android.to_do_list_2_0.viewModel.myViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Config(constants = BuildConfig.class)
public class viewModel_helper_functions_test {


    private myViewModel viewModel;
    String time;

    @Before
    public void init(){
        viewModel = mock(myViewModel.class);
    }

    @Test
    public void testGetMinute0(){
        time = "1:00PM";
        when(viewModel.getMinute(time, time.indexOf(':'))).thenReturn("00");
        assertEquals("00", viewModel.getMinute(time, time.indexOf(":")));
    }

    @Test
    public void testGetMinute1(){
        time = "1:05PM";
        when(viewModel.getMinute(time, time.indexOf(':'))).thenReturn("05");
        assertEquals("05", viewModel.getMinute(time, time.indexOf(":")));
    }

    @Test
    public void testGetMinute3(){
        time = "1:59PM";
        when(viewModel.getMinute(time, time.indexOf(':'))).thenReturn("59");
        assertEquals("59", viewModel.getMinute(time, time.indexOf(":")));
    }

    @Test
    public void testGetMinute4(){
        time = "1:34PM";
        when(viewModel.getMinute(time, time.indexOf(':'))).thenReturn("34");
        assertEquals("34", viewModel.getMinute(time, time.indexOf(":")));
    }

    @Test
    public void testGetHourEdgeCase0(){
        time = "1:00PM";
        when(viewModel.getHour(time,  time.indexOf(':'))).thenReturn("13");
        assertEquals("13", viewModel.getHour(time,  time.indexOf(':')));
    }

    @Test
    public void testGetHourEdgeCase1(){
        time = "10:00PM";
        when(viewModel.getHour(time,  time.indexOf(':'))).thenReturn("22");
        assertEquals("22", viewModel.getHour(time,  time.indexOf(':')));
    }

    @Test
    public void testGetHourEdgeCase2(){
        time = "12:00AM";
        when(viewModel.getHour(time,  time.indexOf(':'))).thenReturn("24");
        assertEquals("24", viewModel.getHour(time,  time.indexOf(':')));
    }

    @Test
    public void testGetHour(){
        time = "12:22PM";
        when(viewModel.getHour(time,  time.indexOf(':'))).thenReturn("12");
        assertEquals("12", viewModel.getHour(time,  time.indexOf(':')));
    }
}
