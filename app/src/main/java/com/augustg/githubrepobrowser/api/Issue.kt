package com.augustg.githubrepobrowser.api

import com.google.gson.annotations.SerializedName

data class Issue(@SerializedName("title") val title: String,
                 @SerializedName("body") val body: String,
                 @SerializedName("number") val number: Int,
                 @SerializedName("comments") val commentCount: Int,
                 @SerializedName("user") val user: User)

data class User(@SerializedName("login") val login: String)