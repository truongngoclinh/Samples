package com.linhtruong.sample

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.*
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.linhtruong.sample.explore.MainActivity
import com.linhtruong.sample.explore.NewsListAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author linhtruong
 */
@RunWith(AndroidJUnit4::class)
class NewsListFragmentTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickNewsItem_openNewsDetail() {
        onView(withId(R.id.rvNews)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsListAdapter.NewsHolder>(0, click()))
        onView(withId(R.id.txtTitle)).check(matches(isDisplayed()))
    }
}