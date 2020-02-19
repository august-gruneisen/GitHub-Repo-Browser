package com.augustg.githubrepobrowser.suite

import com.augustg.githubrepobrowser.IssuesFragmentTest
import com.augustg.githubrepobrowser.MainActivityTest
import com.augustg.githubrepobrowser.ReposFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityTest::class,
    IssuesFragmentTest::class,
    ReposFragmentTest::class)
class ComponentIsolationSuite