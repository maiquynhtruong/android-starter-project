package com.mycompany.myapp.data.api.github

import com.mycompany.myapp.data.api.github.GitHubInteractor.LoadCommitsRequest
import com.mycompany.myapp.data.api.github.GitHubInteractor.LoadCommitsResponse
import com.mycompany.myapp.data.api.github.model.CommitTestHelper.stubCommit
import com.mycompany.myapp.data.api.github.model.JobTestHelper.stubJob
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import retrofit2.Response
import java.util.Arrays.asList
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class GitHubInteractorTest {

    @Mock lateinit var api: GitHubApiService

    private lateinit var interactor: GitHubInteractor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val context = RuntimeEnvironment.application
        interactor = GitHubInteractor(context, api)
    }

//    @Test
//    @Throws(Exception::class)
//    fun testLoadCommits() {
//        val mockResponse = Single.just(Response.success(asList(stubCommit("test name", "test message"))))
//        whenever(api.listCommits(anyString(), anyString())).thenReturn(mockResponse)
//
//        val subscriber = TestObserver<LoadCommitsResponse>()
//        interactor.loadCommits(LoadCommitsRequest("user", "repo")).subscribeWith(subscriber)
//        subscriber.await(1, TimeUnit.SECONDS)
//
//        subscriber.assertValueCount(1)
//        subscriber.assertNoErrors()
//        subscriber.assertComplete()
//
//        val response = subscriber.values()[0]
//        assertEquals("user", response.request.user)
//        assertEquals("repo", response.request.repository)
//        assertEquals(1, response.commits.size.toLong())
//
//        val commit = response.commits[0]
//        assertEquals("test name", commit.author)
//        assertEquals("test message", commit.commitMessage)
//    }

    @Test
    @Throws(Exception::class)
    fun testLoadJobs() {
        val mockResponse = Single.just(Response.success(asList(stubJob("Lead Software Architect",
                "New York, NY", "Full TIme","The Expert Institute", "Bla"))))
        whenever(api.listJobs(anyString(), anyString())).thenReturn(mockResponse)

        val subscriber = TestObserver<GitHubInteractor.LoadJobsResponse>()
        interactor.loadJobs(GitHubInteractor.LoadJobsRequest("mobile", "new+york")).subscribeWith(subscriber)
        subscriber.await(1, TimeUnit.SECONDS)

        subscriber.assertValueCount(1)
        subscriber.assertNoErrors()
        subscriber.assertComplete()

        val response = subscriber.values()[0]
        assertEquals("mobile", response.request.keyword)
        assertEquals("new+york", response.request.location)
        assertEquals(1, response.jobs.size.toLong())

        val job = response.jobs[0]
        assertEquals("Lead Software Architect", job.title)
        assertEquals("New York, NY", job.location)
        assertEquals("Full TIme", job.type)
        assertEquals("The Expert Institute", job.company)
        assertEquals("Bla", job.description)

    }
}