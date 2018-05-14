package com.mycompany.myapp.ui.main

import android.app.Application
import android.content.Intent
import android.databinding.Bindable
import android.os.Parcelable
import android.support.annotation.VisibleForTesting
import com.mycompany.myapp.BR
import com.mycompany.myapp.BuildConfig
import com.mycompany.myapp.R
import com.mycompany.myapp.data.api.github.GitHubInteractor
import com.mycompany.myapp.data.api.github.GitHubInteractor.LoadCommitsRequest
import com.mycompany.myapp.data.api.github.model.Commit
import com.mycompany.myapp.data.api.github.model.Job
import com.mycompany.myapp.ui.BaseViewModel
import com.mycompany.myapp.ui.SimpleSnackbarMessage
import com.mycompany.myapp.ui.details.DetailsActivity
import com.mycompany.myapp.util.RxUtils.delayAtLeast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
        private val app: Application,
        private val gitHubInteractor: GitHubInteractor,
        @Named("loading_delay_ms") private val loadingDelayMs: Long)
    : BaseViewModel<MainViewModel.State>(app, STATE_KEY, State()), MainFragment.JobsAdapter.ClickHandler {

    override fun onJobClicked(job: Job) {
        val intent = Intent(app, DetailsActivity::class.java).apply {
            putExtra("title", job.title)
            putExtra("company", job.company)
            putExtra("location", job.location)
            putExtra("type", job.type)
            putExtra("description", job.description)
        }
        app.startActivity(intent)
    }

    @Parcelize
    class State(
            var keyword: String = "",
            var location: String = "") : Parcelable

    sealed class Jobs {
        class Loading : Jobs()
        class Result(val jobs: List<Job>) : Jobs()
        class Error(val message: String) : Jobs()
    }

    override fun setupViewModel() {
        keyword = "mobile"  // NON-NLS
        location = "new+york"  // NON-NLS

        fetchJobs()
    }

    @VisibleForTesting
    internal var jobs: Jobs = Jobs.Result(emptyList())
        set(value) {
            field = value

            notifyPropertyChanged(BR.loading)
            notifyPropertyChanged(BR.jobs)
            notifyPropertyChanged(BR.fetchJobsEnabled)

            when (value) {
                is Jobs.Error -> snackbarMessage.value = value.message
            }
        }

    val snackbarMessage = SimpleSnackbarMessage()

    var keyword: String
        @Bindable get() = state.keyword
        set(value) {
            state.keyword = value
            notifyPropertyChanged(BR.keyword)
        }

    var location: String
        @Bindable get() = state.location
        set(value) {
            state.location = value
            notifyPropertyChanged(BR.location)
        }

    @Bindable("keyword", "location")
    fun isFetchJobsEnabled(): Boolean = jobs !is Jobs.Loading && !keyword.isEmpty() && !location.isEmpty()

    @Bindable
    fun isLoading(): Boolean = jobs is Jobs.Loading

    @Bindable
    fun getJobs() = jobs.let {
        when (it) {
            is Jobs.Result -> it.jobs
            else -> emptyList()
        }
    }

    fun getVersion(): String = BuildConfig.VERSION_NAME

    fun getFingerprint(): String = BuildConfig.VERSION_FINGERPRINT

    companion object {
        private const val STATE_KEY = "MainViewModelState"  // NON-NLS
    }

    fun fetchJobs() {
        jobs = Jobs.Loading()
        disposables.add(delayAtLeast(gitHubInteractor.loadJobs(GitHubInteractor.LoadJobsRequest(keyword, location)), loadingDelayMs)
                .map { it.jobs }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { jobs = Jobs.Result(it) },
                        { jobs = Jobs.Error(it.message ?: app.getString(R.string.error_unexpected)) }
                ))
    }
}
