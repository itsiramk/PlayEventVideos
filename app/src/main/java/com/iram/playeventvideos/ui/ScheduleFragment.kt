package com.iram.playeventvideos.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iram.newsheadlines.utils.Resource
import com.iram.playeventvideos.R
import com.iram.playeventvideos.adapters.ScheduleListAdapter
import com.iram.playeventvideos.databinding.LayoutRviewBinding
import com.iram.playeventvideos.model.EventSchedule
import com.iram.playeventvideos.utils.CustomComparator
import com.iram.playeventvideos.utils.DateFormat
import com.iram.playeventvideos.utils.autoCleared
import com.iram.playeventvideos.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refreshData()
    }

    private fun refreshData() {
        val handler = Looper.myLooper()?.let { Handler(it) }
        val delay = 30 * 1000
        handler?.postDelayed({ scheduleListAdapter.notifyDataSetChanged() }, delay.toLong())
    }

    private fun fetchData() {
        binding.pBar.visibility = View.VISIBLE
        var tomorrowScheduleList = ArrayList<EventSchedule>()
        scheduleViewModel.res.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.pBar.visibility = View.GONE
                    if (it?.data != null && it.data.isNotEmpty()) {
                        tomorrowScheduleList = ArrayList(it.data)
                        for (i in it.data) {
                            val date = DateFormat.getTomorrowsDate(DateFormat.stringToDate(i.date))
                            if (!date) {
                                tomorrowScheduleList.remove(i)
                            }
                        }
                        if (tomorrowScheduleList.isNotEmpty()) {
                            Collections.sort(tomorrowScheduleList, CustomComparator())
                            binding.tvNoData.visibility = View.GONE
                            scheduleListAdapter.setItems(tomorrowScheduleList)
                        }else{
                            binding.tvNoData.text = requireContext().getString(R.string.no_data)
                            binding.tvNoData.visibility = View.VISIBLE
                        }
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