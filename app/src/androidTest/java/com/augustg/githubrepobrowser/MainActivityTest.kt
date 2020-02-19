package com.augustg.githubrepobrowser

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val activityScenario: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_activityCreated() {
        onView(withId(R.id.main_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_repoFragmentInView() {
        onView(withId(R.id.repos_layout)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}