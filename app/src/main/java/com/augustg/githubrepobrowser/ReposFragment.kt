package com.augustg.githubrepobrowser


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.augustg.githubrepobrowser.adapters.ReposAdapter
import kotlinx.android.synthetic.main.fragment_repos.*

/**
 *
 * The logic here is very simple.
 *
 * Just initialize a RecyclerView that will hold repos,
 * pass the adapter a ViewModel to observe data,
 * and define a click handler.
 *
 */
class ReposFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel by viewModels<GitHubViewModel>()

        repos_recyclerview.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = ReposAdapter(viewModel, repoLongClickListener = { _, name: String ->

                        // specifies an action to be taken on long clicking a repo
                        showIssues(name)

                    })
        }

        /**
         *
         * Once the activity is created and view is initialized,
         * we ask the network for repositories and observe the response.
         *
         * There's no need to specify data here,
         * as the adapter binds to data directly from the ViewModel.
         *
         * Simply notifying the adapter once data changes will allow it to create and bind views once.
         * This is more readable and simpler to implement than DiffUtil,
         * but less efficient if data will be fetched multiple times (pagination).
         *
         */
        viewModel.fetchRepos()
        viewModel.repos.observe(this, Observer {
            repos_recyclerview.adapter?.notifyDataSetChanged()
        })

    }

    // on long clicking a repo, navigate to the next fragment and pass its name
    private fun showIssues(name: String) {
        findNavController().navigate(ReposFragmentDirections.showIssues(name))
    }


}
