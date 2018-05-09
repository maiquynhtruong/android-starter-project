package com.mycompany.myapp.data.api.github

import com.mycompany.myapp.data.api.github.model.Commit
import com.mycompany.myapp.data.api.github.model.Job

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("repos/{user}/{repository}/commits")
    fun listCommits(
            @Path("user") user: String,
    @Path("repository") repository: String): Single<Response<List<Commit>>>

    @GET("positions.json/search={keyword}&page=1")
    fun listJobs(
            @Query("keyword") keyword: String
    ): Single<Response<List<Job>>>
}
