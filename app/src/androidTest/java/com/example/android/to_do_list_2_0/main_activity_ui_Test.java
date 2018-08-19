package com.example.android.to_do_list_2_0;

import android.app.Activity;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.android.to_do_list_2_0.myActivities.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class main_activity_ui_Test {



    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);



    @Test
    public void testAddButton(){
        onView(withId(R.id.add_button)).check(matches(isDisplayed()));
    }

    //Start the activity addToDo, make sure 2 buttons exist and keyboard pops up
    @Test
    public void testAddToDoLayout(){
        //Click + button on home screen
        onView(withId(R.id.add_button)).perform(click());
        //Check if fragment started
        onView(withId(R.id.activity_add_to_do)).check(matches(isDisplayed()));

        //Check 2 buttons exist
        onView(withId(R.id.button_add_todo)).check(matches(isDisplayed()));
        onView(withId(R.id.button_add_time)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddToDoBasic() {

        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recycler_view);
        int itemcount = recyclerView.getAdapter().getItemCount();
        //Start addToDo activity
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.edit_text_add_todo)).perform(typeText("11 Todo!"));
        onView(withId(R.id.button_add_todo)).perform(click());

        //Check item was correctly added to recyclerview
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(7));
        onView(withId(R.id.recycler_view)).perform(click());

        try {
            Thread.sleep(2000); // this is needed :(
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRecyclerView(){

        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recycler_view);
        for(int i = 0; i < recyclerView.getChildCount(); i++){
            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
        }
        //onView(withId(R.id.recycler_view)).perform(scrollToPosition(recyclerView.getChildCount()));
    }
}
