package com.iram.playeventvideos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.iram.playeventvideos.adapters.ScheduleListAdapter
import com.iram.playeventvideos.databinding.FragmentScheduleBinding
import com.iram.playeventvideos.utils.autoCleared
import com.iram.playeventvideos.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment(), ScheduleListAdapter.ScheduleItemListener {

    private lateinit var scheduleViewModel: ScheduleViewModel
    private var binding: FragmentScheduleBinding by autoCleared()
    private lateinit var scheduleListAdapter: ScheduleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchData()
    }

    private fun fetchData() {
        scheduleViewModel.res.observe(viewLifecycleOwner, {
            if (it?.data != null && it.data.isNotEmpty())
                scheduleListAdapter.setItems(it.data)
        })
    }

    private fun initViews() {
        scheduleListAdapter = ScheduleListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.adapter = scheduleListAdapter
    }

    override fun onClickedItemData(title: String) {
    }

}