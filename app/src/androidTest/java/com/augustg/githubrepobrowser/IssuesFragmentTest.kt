package com.augustg.githubrepobrowser

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class IssuesFragmentTest {

    @Test
    fun test_fragmentCreated() {
        val args = Bundle().apply {
            this.putString("name", "ami-query")
        }
        val scenario: FragmentScenario<IssuesFragment> = launchFragmentInContainer<IssuesFragment>(args)
        onView(withId(R.id.issues_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_initial_truffleShuffleDisplayed() {
        val args = Bundle().apply {
            this.putString("name", "ami-query")
        }
        val scenario: FragmentScenario<IssuesFragment> = launchFragmentInContainer<IssuesFragment>(args)
        onView(withId(R.id.truffle_shuffle)).check(matches(isDisplayed()))
    }
}