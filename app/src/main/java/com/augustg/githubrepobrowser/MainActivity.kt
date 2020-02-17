package com.augustg.githubrepobrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Loves me an empty Activity :D

        /**
         * I chose to use a NavHostFragment with @navigation/nav_graph.xml
         *
         * The entry point is a fragment displaying a list of repos.
         * Clicking a repo will show details like stars, watchers, issue count, forks count, etc...
         *
         * Long clicking will cause the NavController to navigate to a new fragment
         * displaying a list of issues specific to that repo.
         */
    }
}