package com.mycompany.myapp.ui.details

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mycompany.myapp.R
import com.mycompany.myapp.ui.BaseFragment
import com.mycompany.myapp.ui.main.MainFragment
import com.mycompany.myapp.ui.main.MainViewModel

class DetailsFragment : BaseFragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsFragmentBinding

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = getViewModel(DetailsViewModel::class)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.vm = viewModel

        return binding.root
    }

    override fun onDestroyView() {
        binding.unbind()
        super.onDestroyView()
    }


}