package com.augustg.githubrepobrowser

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ReposFragmentTest {

    @Test
    fun test_fragmentCreated() {
        val scenario: FragmentScenario<ReposFragment> = launchFragmentInContainer<ReposFragment>()
        onView(withId(R.id.repos_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_initial_recyclerViewVisible() {
        val scenario: FragmentScenario<ReposFragment> = launchFragmentInContainer<ReposFragment>()
        onView(withId(R.id.repos_recyclerview)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

}