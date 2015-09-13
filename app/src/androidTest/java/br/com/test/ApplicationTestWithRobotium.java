package br.com.test;

import android.app.Application;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.Is;

import java.util.EnumSet;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@LargeTest
    public class ApplicationTestWithRobotium extends ActivityInstrumentationTestCase2 {

    public ApplicationTestWithRobotium() {
        super(MainActivity.class);
    }

    private Solo solo;

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testSimpleClickAndCheckText() {

        solo.waitForActivity(MainActivity.class);
        assertTrue(solo.waitForText("Fisrt Test", 1, 5000));
        solo.enterText((EditText) solo.getView(R.id.editTextId), "Pablo");
        solo.clickOnView(solo.getView(R.id.buttonId));
        solo.waitForActivity(HelloActivity.class);
        assertTrue(solo.waitForText("Hello World!", 1, 5000));
        assertTrue(solo.waitForText("Pablo", 1, 5000));

    }

}