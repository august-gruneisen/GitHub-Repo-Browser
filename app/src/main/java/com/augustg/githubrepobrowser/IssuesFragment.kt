package com.augustg.githubrepobrowser


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.augustg.githubrepobrowser.adapters.IssuesAdapter
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

        issues_recyclerview.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = IssuesAdapter(viewModel, issueClickListener = { _, position: Int ->

                        Toast.makeText(activity?.applicationContext, position.toString(), Toast.LENGTH_SHORT).show()

                    })
        }

        viewModel.fetchIssues(args.name)
        viewModel.issues.observe(this, Observer {
            issues_recyclerview.adapter?.notifyDataSetChanged()
        })
    }

}
