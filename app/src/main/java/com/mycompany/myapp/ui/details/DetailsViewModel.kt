package com.mycompany.myapp.ui.details

import android.app.Application
import android.content.Intent
import android.databinding.Bindable
import android.os.Parcelable
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
        this.job = job
    }

}