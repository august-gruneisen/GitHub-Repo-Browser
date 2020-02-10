package com.augustg.githubrepobrowser.api

import com.google.gson.annotations.SerializedName

data class Repo(@SerializedName("name") val name: String,
                @SerializedName("description") val description: String,
                @SerializedName("stargazers_count") val starCount: Int,
                @SerializedName("watchers_count") val watcherCount: Int,
                @SerializedName("open_issues_count") val issueCount: Int,
                @SerializedName("forks") val forkCount: Int)