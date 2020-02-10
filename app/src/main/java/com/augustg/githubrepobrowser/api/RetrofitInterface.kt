package com.augustg.githubrepobrowser.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {

    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") username: String): Call<List<Repo>>

    @GET("repos/{owner}/{repo}/issues")
    fun getIssues(@Path("owner") owner: String, @Path("repo") repo: String): Call<List<Issue>>

    companion object {

        private const val BASE_URL = "https://api.github.com/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun client() : RetrofitInterface {
            return retrofit.create(RetrofitInterface::class.java)
        }

    }
}