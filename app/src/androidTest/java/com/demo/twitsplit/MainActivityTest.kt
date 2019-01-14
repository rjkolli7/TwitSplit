package com.demo.twitsplit

import android.support.test.espresso.action.ViewActions
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.IsNot.not

/**
 * This test will execute on an Android Device or Emulator.
 * */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    /**
     * check views visibility and tweet_send_tv is not enabled by default.
     *
     * This only pass if all views are visible and tweet_send_tv disabled, not fail.
     * */
    @Test
    fun checkViews() {
        onView(withId(R.id.tweet_msg_edt)).check(matches(isDisplayed()))
        onView(withId(R.id.tweet_send_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.tweet_send_tv)).check(matches(not(isEnabled())))
    }

    /**
     * test error message sending
     *
     * This only pass if UI shows error dialog.
     * */
    @Test
    fun sendErrorMessage() {
        val message = "Ican'tbelieveTweeternowsupportschunkingmymessages,soIdon'thavetodoitmyself."
        onView(withId(R.id.tweet_msg_edt)).perform(typeText(message), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.tweet_send_tv)).perform(click())
    }

    /**
     * test error message sending
     *
     * This only pass if UI shows success dialog with single message.
     * */
    @Test
    fun sendNonWhiteSpaceMessageLess50Char() {
        val message = "Ican'tbelieveTweeternowsupportschunkingmymessages."
        onView(withId(R.id.tweet_msg_edt)).perform(typeText(message), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.tweet_send_tv)).perform(click())
    }

    /**
     * test error message sending
     *
     * This only pass if UI shows success dialog with single message.
     * */
    @Test
    fun sendMessage50CharLess() {
        val message = "I can't believe Tweeter now supports chunking."
        onView(withId(R.id.tweet_msg_edt)).perform(typeText(message), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.tweet_send_tv)).perform(click())
    }

    /**
     * test error message sending
     *
     * This only pass if UI shows success dialog with chunk message parts.
     * */
    @Test
    fun sendSuccessMessage() {
        val message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        onView(withId(R.id.tweet_msg_edt)).perform(typeText(message), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.tweet_send_tv)).perform(click())
    }
}
