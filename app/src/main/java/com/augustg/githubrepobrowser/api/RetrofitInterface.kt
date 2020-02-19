package com.augustg.githubrepobrowser.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/users/{username}/repos")
    suspend fun getRepos(@Path("username") username: String)
            : Response<List<Repo>>

    @GET("repos/{owner}/{repo}/issues")
    suspend fun getIssues(@Path("owner") owner: String, @Path("repo") repo: String, @Query("type") type: String)
            : Response<List<Issue>>

    companion object {

        private const val BASE_URL = "https://api.github.com/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun client(): RetrofitInterface {
            return retrofit.create(RetrofitInterface::class.java)
        }
    }
}