package br.com.test;
 
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.WindowManager;

import com.robotium.solo.Solo;
 
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by pablo on 26/08/15.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class ApplicationTesWithEspresso {
 
    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
//    @UiThreadTest
    public void before() {
        final Activity activity = mActivityRule.getActivity();
        try {
            mActivityRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    KeyguardManager mKG = (KeyguardManager) activity.getSystemService(Context.KEYGUARD_SERVICE);
//                    KeyguardManager mLock = mKG.newKeyguardLock(KEYGUARD_SERVICE);
//                    mLock.disableKeyguard();

                    //turn the screen on
                    activity.getWindow().addFlags(
                            /*WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                            | */WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD/*
                            | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                            | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON*/);
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

//    @Override
//    public void setUp() throws Exception {
//        super.setUp();
//        before();
//        getActivity();
//    }

    @Test
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
                                withText("Hello World!")
                        )));

        onView(withId(R.id.userId))
                .check(matches(
                        AllOf.allOf(
                                isDisplayed(),
                                withText("Pablo")
                        )));
    }

}
