package com.mycompany.myapp.ui.details

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.PersistableBundle
import com.mycompany.myapp.R
import com.mycompany.myapp.data.api.github.model.Job
import com.mycompany.myapp.ui.BaseActivity

class DetailsActivity: BaseActivity() {
    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsActivityBinding

    lateinit var title: String
    lateinit var company: String
    lateinit var type: String
    lateinit var location: String
    lateinit var description: String

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = getViewModel(DetailsViewModel::class)
        viewModel.restoreState(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.vm = viewModel
        binding.executePendingBindings()

        // pull job detail off bundle
        title = savedInstanceState?.getString("title") ?: ""
        company = savedInstanceState?.getString("company") ?: ""
        type = savedInstanceState?.getString("type") ?: ""
        location = savedInstanceState?.getString("location") ?: ""
        description = savedInstanceState?.getString("description") ?: ""

        // Next, do this
        var job: Job = Job(title, location, type, company, description)
        viewModel.updateJobDetail(job)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.saveState(outState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }
}