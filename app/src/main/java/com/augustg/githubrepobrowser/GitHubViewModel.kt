package com.augustg.githubrepobrowser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augustg.githubrepobrowser.api.Issue
import com.augustg.githubrepobrowser.api.Repo
import com.augustg.githubrepobrowser.api.RetrofitInterface
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
     * If the network request is successful, store the returned list in the view model
     */
    fun fetchRepos() {
        retrofit.getRepos(user).enqueue(object: Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) {
                    _repos.value = response.body() as MutableList<Repo>
                    Log.i("retrofit", response.body().toString())
                } else
                    Log.i("retrofit", response.code().toString())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.i("retrofit", t.message.toString())
            }
        })
    }

    /**
     * Fetches issues from the network
     * If the network request is successful, store the returned list in the view model
     */
    fun fetchIssues(repo: String) {
        retrofit.getIssues(user, repo, "all").enqueue(object: Callback<List<Issue>> {
            override fun onResponse(call: Call<List<Issue>>, response: Response<List<Issue>>) {
                if (response.isSuccessful) {
                    _issues.value = response.body() as MutableList<Issue>
                    Log.i("retrofit", response.body().toString())
                } else
                    Log.i("retrofit", response.code().toString())
            }

            override fun onFailure(call: Call<List<Issue>>, t: Throwable) {
                Log.i("retrofit", t.message.toString())
            }
        })
    }
}