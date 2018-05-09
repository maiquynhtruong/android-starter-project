package com.mycompany.myapp.data.api.github

import android.content.Context
import com.mycompany.myapp.Mockable
import com.mycompany.myapp.R
import com.mycompany.myapp.data.api.github.model.Commit
import com.mycompany.myapp.data.api.github.model.Job

import io.reactivex.Observable
import retrofit2.Response
import timber.log.Timber

@Mockable
class GitHubInteractor(
        private val context: Context,
        private val api: GitHubApiService) {

    // for commits
    class LoadCommitsRequest(val user: String, val repository: String)
    class LoadCommitsResponse(val request: LoadCommitsRequest, val commits: List<Commit>)

    fun loadCommits(request: LoadCommitsRequest): Observable<LoadCommitsResponse> {
        return api.listCommits(request.user, request.repository)
                .toObservable()
                .map { response -> checkResponse(response, context.getString(R.string.error_get_commits_error)) }
                .map { response -> response.body() ?: emptyList() }
                .map { commits -> LoadCommitsResponse(request, commits) }
                .doOnError { error -> Timber.e(error) }
    }

    private fun <T> checkResponse(response: Response<T>, message: String): Response<T> {
        return when {
            response.isSuccessful -> response
            else -> throw IllegalStateException(message)
        }
    }

    // for jobs
    class LoadJobsRequest(val keyword: String, val location: String)
    class LoadJobsResponse(val request: LoadJobsRequest, val jobs: List<Job>)

    fun loadJobs(request: LoadJobsRequest): Observable<LoadJobsResponse> {
        return api.listJobs(request.keyword, request.location)
                .toObservable()
                .map { response -> checkResponse(response, context.getString(R.string.error_get_jobs_error)) }
                .map { response -> response.body() ?: emptyList() }
                .map { jobs -> LoadJobsResponse(request, jobs) }
                .doOnError { e -> Timber.e(e) }
    }
}
