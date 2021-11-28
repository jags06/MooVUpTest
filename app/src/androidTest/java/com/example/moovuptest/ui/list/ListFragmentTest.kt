package com.example.moovuptest.ui.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moovuptest.BaseActivity
import com.example.moovuptest.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListFragmentTest {

    /*RecyclerView comes into the view*/
    @get:Rule
    val activityRule = ActivityScenarioRule(BaseActivity::class.java)
    val LIST_ITEM_TEXT = 5

    @Test
    fun test_isListFragmentVisible_onAppLauch() {
        onView(withId(R.id.usersList)).check(matches(isDisplayed()))
    }
    /*Select any item open detail fragment press back button*/
    @Test
    fun test_SelectListItem_isDetailFragmetVisible() {
      //  onView(withId(R.id.usersList)).perform(actionOnItemAtPosition<UserListAdapter>)
    }
}