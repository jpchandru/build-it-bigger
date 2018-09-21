package com.udacity.gradle.builditbigger;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by cj on 9/20/18.
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule(MainActivity.class);

    @Test
    public void buttonClickTest() {
        Espresso.onView(withId(R.id.button_view)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.random_joke)).check(matches(isDisplayed()));
    }


    /*@Test
    public void endpointsAsyncTaskTest() {
        *//*onView(withId(R.id.button_view))
                .check(matches(withText(R.string.button_text)));*//*

        String result = null;
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute();

        try {
            //Context context = InstrumentationRegistry.getContext();
            result = endpointsAsyncTask.get();
            Log.d("Result: ", "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }*/


}
