package com.linhtruong.sample.explore


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.linhtruong.sample.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ExpressoRecordTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun expressoRecordTest() {
        val recyclerView = onView(
                allOf(withId(R.id.rvNews),
                        childAtPosition(
                                withId(R.id.newsListFragment),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val textView = onView(
                allOf(withId(R.id.txtSummary), withText("Details of working conditions at Amazon led to a response from employees, relatives and friends."),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                2),
                        isDisplayed()))
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
                allOf(withId(R.id.txtTitle), withText("Depiction of Amazon Stirs a Debate About Work Culture"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                0),
                        isDisplayed()))
        textView2.check(matches(isDisplayed()))

        val imageView = onView(
                allOf(withId(R.id.imgNews),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                1),
                        isDisplayed()))
        imageView.check(matches(isDisplayed()))

        val button = onView(
                allOf(withId(R.id.btnStoryLink),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container),
                                        0),
                                3),
                        isDisplayed()))
        button.check(matches(isDisplayed()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(2000)

        val relativeLayout = onView(
                allOf(withId(R.id.toolbarBack),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        0),
                                0),
                        isDisplayed()))
        relativeLayout.perform(click())

        Thread.sleep(2000)

        val recyclerView2 = onView(
                allOf(withId(R.id.rvNews),
                        childAtPosition(
                                allOf(withId(R.id.newsListFragment),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                                                0)),
                                0),
                        isDisplayed()))
        recyclerView2.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
