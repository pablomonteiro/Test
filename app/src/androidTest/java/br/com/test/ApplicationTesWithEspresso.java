package br.com.test;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.robotium.solo.Solo;

import org.hamcrest.core.AllOf;

/**
 * Created by pablo on 26/08/15.
 */
@LargeTest
public class ApplicationTesWithEspresso extends ActivityInstrumentationTestCase2 {

    public ApplicationTesWithEspresso() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
    }

    public void test() {

        assertMainActivity();
        onView(withId(R.id.buttonId)).perform(click());
        assertHelloActivity();

    }

    private void assertMainActivity() {
        onView(withId(R.id.firstTestId))
                .check(matches(
                        AllOf.allOf(
                                isDisplayed(),
                                withText("Fisrt Test!")
                        )));

        onView(withId(R.id.editTextId)).perform(
                typeText("Pablo"), closeSoftKeyboard()
        );
    }

    private void assertHelloActivity() {
        onView(withId(R.id.helloId))
                .check(matches(
                        AllOf.allOf(
                                isDisplayed(),
                                withText("Hello World")
                        )));

        onView(withId(R.id.userId))
                .check(matches(
                        AllOf.allOf(
                                isDisplayed(),
                                withText("Pablo")
                        )));
    }

}
