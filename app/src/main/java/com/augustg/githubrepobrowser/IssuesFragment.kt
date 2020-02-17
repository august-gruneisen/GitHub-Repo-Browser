package com.augustg.githubrepobrowser


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.augustg.githubrepobrowser.api.Issue
import com.augustg.githubrepobrowser.adapters.IssuesAdapter
import com.intuit.truffleshuffle.CardViewGroup
import kotlinx.android.synthetic.main.fragment_issues.*

class IssuesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_issues, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel by viewModels<GitHubViewModel>()
        val args: IssuesFragmentArgs by navArgs()

        // Fetch issues and observe response
        viewModel.fetchIssues(args.name)
        viewModel.issues.observe(this, Observer {
            setupCardGallery(it)
        })
    }

    // This layout will be used when an issue is selected
    private fun getInnerCardLayoutDetail(): Int = R.layout.issue_card_detail

    // This layout will be used when no issues are selected
    private fun getInnerCardLayoutDashboard(): Int = R.layout.issue_card_dashboard

    // Sets up a Truffle Shuffle gallery of issue cards :)
    private fun setupCardGallery(it: MutableList<Issue>) {
        val cardLayout = card_gallery_percentage_view_group as CardViewGroup
        IssuesAdapter(
            it as ArrayList<Issue>,
            card_gallery_percentage_view_group.context,
            getInnerCardLayoutDetail(),
            getInnerCardLayoutDashboard() // this fourth parameter demos the added feature
        )
            .setupAdapter(cardLayout)
    }
}