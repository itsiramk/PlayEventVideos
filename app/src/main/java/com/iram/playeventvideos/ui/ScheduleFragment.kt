package com.iram.playeventvideos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iram.newsheadlines.utils.Resource
import com.iram.playeventvideos.adapters.ScheduleListAdapter
import com.iram.playeventvideos.databinding.LayoutRviewBinding
import com.iram.playeventvideos.utils.autoCleared
import com.iram.playeventvideos.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {

    private lateinit var scheduleViewModel: ScheduleViewModel
    private var binding: LayoutRviewBinding by autoCleared()
    private lateinit var scheduleListAdapter: ScheduleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        binding = LayoutRviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchData()
    }

    private fun fetchData() {
        binding.pBar.visibility = View.VISIBLE
        scheduleViewModel.res.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pBar.visibility = View.GONE
                    if (it?.data != null && it.data.isNotEmpty()) {
                        binding.tvNoData.visibility = View.GONE
                        scheduleListAdapter.setItems(it.data)
                    }
                }
                Resource.Status.ERROR -> {
                    if (scheduleListAdapter.itemCount == 0)
                        binding.tvNoData.visibility = View.VISIBLE
                }
                Resource.Status.LOADING ->
                    binding.pBar.visibility = View.VISIBLE
            }
        })
    }

    private fun initViews() {
        scheduleListAdapter = ScheduleListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = scheduleListAdapter
    }
}