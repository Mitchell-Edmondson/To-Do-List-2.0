package com.example.android.to_do_list_2_0;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TimePicker;

import com.example.android.to_do_list_2_0.Activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class viewModel_GetHourTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
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

    @Test
    public void testIntent_ActivityAddToDo(){

    }

}
