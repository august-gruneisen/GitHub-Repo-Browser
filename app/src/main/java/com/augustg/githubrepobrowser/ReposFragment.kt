package com.augustg.githubrepobrowser


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.augustg.githubrepobrowser.adapters.ReposAdapter
import com.augustg.githubrepobrowser.databinding.FragmentReposBinding
import kotlinx.android.synthetic.main.fragment_repos.*

/**
 * Just initializes a RecyclerView that will hold repos,
 * passes the adapter a ViewModel to observe data,
 * and defines a click handler.
 */
class ReposFragment : Fragment() {

    private lateinit var binding: FragmentReposBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReposBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel by viewModels<GitHubViewModel>()
        binding.viewModel = viewModel

        repos_recyclerview.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = ReposAdapter(viewModel, repoLongClickListener = { _, name: String ->
                        // specifies the action to be taken on long clicking a repo
                        showIssues(name)
                    })
        }

        /**
         * Once the activity is created and view is initialized,
         * we ask the network for repositories and observe the response.
         *
         * There's no need to specify data here,
         * as the adapter binds to data directly from the ViewModel.
         *
         * Simply notifying the adapter once data changes will allow it to create and bind views once.
         * This is more readable and simpler to implement than DiffUtil,
         * but less efficient if data will be fetched multiple times (pagination).
         */
        viewModel.fetchRepos()
        viewModel.repos.observe(this, Observer {
            repos_recyclerview.adapter?.notifyDataSetChanged()
        })

        // observe network errors and toast response code
        viewModel.networkErrorMessage.observe(this, Observer { event ->
            event?.getContent()?.let { Toast.makeText(activity?.applicationContext, it, Toast.LENGTH_LONG).show() }
        })
    }

    /**
     * Navigate to the next fragment passing the selected repo's name
     */
    private fun showIssues(name: String) {
        findNavController().navigate(ReposFragmentDirections.showIssues(name))
    }
}
