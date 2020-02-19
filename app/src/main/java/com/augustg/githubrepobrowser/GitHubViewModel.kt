package com.augustg.githubrepobrowser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.augustg.githubrepobrowser.api.Issue
import com.augustg.githubrepobrowser.api.Repo
import com.augustg.githubrepobrowser.api.RetrofitInterface
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubViewModel : ViewModel() {

    private val _repos by lazy {
        MutableLiveData<MutableList<Repo>>(mutableListOf())
    }

    val repos: LiveData<MutableList<Repo>>
        get() = _repos

    private val _issues by lazy {
        MutableLiveData<MutableList<Issue>>(mutableListOf())
    }

    val issues: LiveData<MutableList<Issue>>
        get() = _issues

    // hardcoded user value for searching repos
    val user = "intuit"

    /**
     * Currently making network calls directly from the ViewModel
     * Future versions should do this from a repository and persist the data
     */
    private val retrofit = RetrofitInterface.client()

    /**
     * Fetches repos from the network
     * If the network request is fulfilled, store the returned list in the view model
     */
    fun fetchRepos() {
        viewModelScope.launch {
            try {
                val response = retrofit.getRepos(user)
                when (response.code()) {
                    200 -> _repos.value = response.body() as MutableList<Repo>
                    else -> Log.i("network", response.toString())
                }
            } catch (exception: Exception) {
                Log.i("network", exception.message.toString())
            }
        }
    }

    /**
     * Fetches issues from the network
     * If the network request is fulfilled, store the returned list in the view model
     *
     * @param repo The selected repository to pull issues from
     */
    fun fetchIssues(repo: String) {
        viewModelScope.launch {
            try {
                val response = retrofit.getIssues(user, repo, "all")
                when (response.code()) {
                    200 -> _issues.value = response.body() as MutableList<Issue>
                    else -> Log.i("network", response.toString())
                }
            } catch (exception: Exception) {
                Log.i("network", exception.message.toString())
            }
        }
    }
}