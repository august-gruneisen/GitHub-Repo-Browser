package com.augustg.githubrepobrowser

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.augustg.githubrepobrowser.api.Issue
import com.augustg.githubrepobrowser.api.Repo
import com.augustg.githubrepobrowser.api.RetrofitInterface
import com.augustg.githubrepobrowser.util.Event
import kotlinx.coroutines.launch

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

    /**
     * Currently making network calls directly from the ViewModel
     * Future versions should do this from a repository and persist the data
     */
    private val retrofit = RetrofitInterface.client()

    // hardcoded user value for searching repos
    val user = "intuit"

    private val _networkErrorMessage = MutableLiveData<Event<String>>()

    val networkErrorMessage: LiveData<Event<String>>
        get() = _networkErrorMessage

    val inProgress = ObservableBoolean()

    /**
     * Fetches repos from the network
     * If the request is fulfilled, store the returned list in the view model
     * If the response code is anything other than 200, send a message to notify observers
     */
    fun fetchRepos() {
        // show progress spinner
        inProgress.set(true)
        // make network call
        viewModelScope.launch {
            try {
                val response = retrofit.getRepos(user)
                when (response.code()) {
                    200 -> _repos.value = response.body() as MutableList<Repo>
                    else -> _networkErrorMessage.value = Event("Uh oh! Network returned response code ${response.code()}")
                }
            } catch (exception: Exception) {
                _networkErrorMessage.value = Event("Oops! ${exception.message.toString()}}")
            }
            // hide progress spinner
            inProgress.set(false)
        }
    }

    /**
     * Fetches issues from the network
     * If the request is fulfilled, store the returned list in the view model
     * If the response code is anything other than 200, send a message to notify observers
     *
     * @param repo The selected repository to pull issues from
     */
    fun fetchIssues(repo: String) {
        // show progress spinner
        inProgress.set(true)
        // make network call
        viewModelScope.launch {
            try {
                val response = retrofit.getIssues(user, repo, "all")
                when (response.code()) {
                    200 -> _issues.value = response.body() as MutableList<Issue>
                    else -> _networkErrorMessage.value = Event("Uh oh! Network returned response code ${response.code()}")
                }
            } catch (exception: Exception) {
                _networkErrorMessage.value = Event("Oops! ${exception.message.toString()}}")
            }
            // hide progress spinner
            inProgress.set(false)
        }
    }
}