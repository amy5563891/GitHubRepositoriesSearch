package com.example.githubrepositoriessearch.http

import com.example.githubrepositoriessearch.model.Results
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BackendAPI {
    @GET("/search/repositories")
    suspend fun getRepositories(@Query("q") q : String) : Response<Results>
}