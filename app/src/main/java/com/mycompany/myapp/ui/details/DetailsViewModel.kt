package com.mycompany.myapp.ui.details

import android.app.Application
import android.content.Intent
import android.databinding.Bindable
import android.os.Parcelable
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import com.mycompany.myapp.data.api.github.model.Job
import com.mycompany.myapp.ui.BaseViewModel
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
        private val app: Application)
    :BaseViewModel<DetailsViewModel.State>(app, STATE_KEY, DetailsViewModel.State()) {

    @Parcelize
    class State (): Parcelable

    companion object {
        private const val STATE_KEY = "DetailsViewModelState"  // NON-NLS
    }

    @Bindable
    var job: Job? = null


    override fun setupViewModel() {

    }

    fun updateJobDetail(job: Job) {
        val jobDescription: String
        if (android.os.Build.VERSION.SDK_INT >= 24) jobDescription = Html.fromHtml(job.description, 0).toString()
        else jobDescription = job.description
        this.job = job.copy(description = jobDescription)
    }

}