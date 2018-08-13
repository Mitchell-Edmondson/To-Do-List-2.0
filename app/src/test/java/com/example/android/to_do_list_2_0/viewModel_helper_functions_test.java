package com.example.android.to_do_list_2_0;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.to_do_list_2_0.myActivities.MainActivity;
import com.example.android.to_do_list_2_0.myActivities.addToDo;
import com.example.android.to_do_list_2_0.viewModel.ViewModel;

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

    @Before
    public void init(){
        MockitoAnnotations.initMocks(ViewModel.class);
    }

    @Test
    public void testViewModel(){
        ViewModel viewModel = mock(ViewModel.class);
        String time = "1:22PM";
        when(viewModel.getHour("12:22PM",  time.indexOf(':'))).thenReturn("12");
        assertEquals("12", viewModel.getHour("12:22PM",  time.indexOf(':')));
    }
}
