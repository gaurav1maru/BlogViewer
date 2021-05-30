package com.example.blogviewer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BlogUITest {
    private val validIndex = 1
    private val invalidIndex = 100

    @get:Rule
    var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    @Before
    fun init() {
        activityTestRule.activity
            .supportFragmentManager.beginTransaction()

        onView(isRoot()).perform(waitFor(2000))
    }

    @Test
    fun scrollToBlogThatExists() {
        val itemElementText = "${
            activityTestRule.activity.resources
                .getString(R.string.ui_test_blog_row)
        }"
        onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(itemElementText))
                )
            )
    }

    @Test
    fun clickBlogList() {
        onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    validIndex,
                    click()
                )
            )
    }

    @Test(expected = PerformException::class)//test will pass as it expects an exception
    fun clickBlogThatDoesNotExist() {
        onView(ViewMatchers.withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    invalidIndex,
                    click()
                )
            )
    }

    private fun waitFor(millis: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}