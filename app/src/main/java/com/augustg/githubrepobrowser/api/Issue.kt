package com.augustg.githubrepobrowser.api

import com.google.gson.annotations.SerializedName

data class Issue(@SerializedName("title") val title: String,
                 @SerializedName("number") val number: Int,
                 @SerializedName("state") val account: String,
                 @SerializedName("comments") val commentCount: Int)